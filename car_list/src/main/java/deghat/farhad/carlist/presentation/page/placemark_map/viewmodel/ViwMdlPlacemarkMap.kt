package deghat.farhad.carlist.presentation.page.placemark_map.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deghat.farhad.carlist.R
import deghat.farhad.carlist.domain.usecase.GetPlacemarks
import deghat.farhad.carlist.presentation.item.MapItmPlacemark
import deghat.farhad.carlist.presentation.mapper.PlacemarkItemMapper
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import deghat.farhad.common.presentation.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViwMdlPlacemarkMap @Inject constructor(
    private val getPlacemarks: GetPlacemarks,
    private val placemarkItemMapper: PlacemarkItemMapper
) : ViewModel() {

    private val mState = MutableLiveData<UiState<List<MapItmPlacemark>>>()
    val state: LiveData<UiState<List<MapItmPlacemark>>> = mState
    private val mapItmPlacemarks: MutableList<MapItmPlacemark> = mutableListOf()
    var isShowingAll = true

    val isPermissionGranted by lazy { MutableLiveData<Boolean>() }
    fun setPermissionGranted(value: Boolean) {
        isPermissionGranted.postValue(value)
    }

    fun getPlacemarks() {
        viewModelScope.launch {
            mState.postValue(UiState.Loading())
            getPlacemarks.execute {
                when (it) {
                    is ModelWrapper.NetworkError -> mState.postValue(
                        UiState.Error(
                            R.string.network_error_message,
                            ::retry
                        )
                    )
                    is ModelWrapper.ServerError -> mState.postValue(
                        UiState.Error(
                            R.string.server_error_message,
                            ::retry
                        )
                    )
                    is ModelWrapper.Success -> {
                        if (it.model.isNotEmpty()) {
                            mapItmPlacemarks.addAll(placemarkItemMapper.mapToMapItmPlacemark(it.model))
                            showAll()
                        } else
                            mState.postValue(UiState.NoData(::refresh))
                    }
                    is ModelWrapper.UnknownError -> mState.postValue(
                        UiState.Error(
                            R.string.unknown_error_message,
                            ::retry
                        )
                    )
                }
            }
        }
    }

    private fun refresh() {
        getPlacemarks()
    }

    private fun retry() {
        getPlacemarks()
    }

    private fun showJust(name: String) {
        isShowingAll = false
        val mapItmPlacemark = mapItmPlacemarks.first { it.name == name }
        mapItmPlacemark.showInfo = true
        mState.postValue(UiState.HasData(listOf(mapItmPlacemark)))
    }

    private fun showAll(name: String? = null) {
        name?.let {
            val mapItmPlacemark = mapItmPlacemarks.first { it.name == name }
            mapItmPlacemark.showInfo = false
        }
        isShowingAll = true
        mState.postValue(UiState.HasData(mapItmPlacemarks))
    }

    fun onMarkerClick(name: String) {
        if (isShowingAll)
            showJust(name)
        else
            showAll(name)
    }
}