package deghat.farhad.carlist.presentation.page.placemark_map.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import deghat.farhad.carlist.BR
import deghat.farhad.carlist.R
import deghat.farhad.carlist.presentation.StateHandler
import deghat.farhad.carlist.presentation.item.MapItmPlacemark
import deghat.farhad.carlist.presentation.page.placemark_map.viewmodel.ViwMdlPlacemarkMap
import deghat.farhad.common.presentation.UiState
import javax.inject.Inject

@AndroidEntryPoint
class FragPlacemarkMap : Fragment(), OnMapReadyCallback {
    companion object {
        const val PERMISSIONS_REQUEST_LOCATION = 101
    }

    @Inject
    lateinit var stateHandler: StateHandler

    private val viewModel: ViwMdlPlacemarkMap by viewModels()

    lateinit var googleMap: GoogleMap
    lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setObservers()
        return setDataBinding(inflater, container)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        return DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.frag_placemark_map,
            container,
            false
        ).apply {
            setVariable(BR.viewmodel, viewModel)
            setVariable(BR.stateHandler, stateHandler)
            lifecycleOwner = this@FragPlacemarkMap
        }.root
    }

    private fun setObservers() {
        viewModel.isPermissionGranted.observe(viewLifecycleOwner) {
            mapView.getMapAsync(this)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->

            if (state is UiState.HasData<List<MapItmPlacemark>>) {
                googleMap.clear()
                state.content.forEach {
                    val marker = googleMap.addMarker(
                        MarkerOptions()
                            .position(
                                LatLng(
                                    it.coordinates.latitude,
                                    it.coordinates.longitude
                                )
                            )
                            .title(it.name)
                    )
                    if (it.showInfo)
                        marker.showInfoWindow()
                }
            }
            stateHandler.setUiState(state)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        mapView = requireActivity().findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_LOCATION
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.setPermissionGranted(true)
            } else {
                viewModel.setPermissionGranted(false)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            this.googleMap.isMyLocationEnabled = true
        }
        googleMap.setOnMarkerClickListener {
            viewModel.onMarkerClick(it.title)
            true
        }
        viewModel.getPlacemarks()
    }
}