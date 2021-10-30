package deghat.farhad.carlist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import deghat.farhad.carlist.BR
import deghat.farhad.carlist.R
import deghat.farhad.carlist.presentation.viewmodel.ViwMdlPlacemarkList
import deghat.farhad.common.presentation.util.recycler_view.GenericRecyclerAdapter

@AndroidEntryPoint
class FragPlacemarkList : Fragment() {

    private val viewModel: ViwMdlPlacemarkList by viewModels()

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
            R.layout.frag_placemark_list,
            container,
            false
        ).apply {
            setVariable(BR.viewmodel, viewModel)
            lifecycleOwner = this@FragPlacemarkList
        }.root
    }

    private fun setObservers() {
        viewModel.placemarks.observe(viewLifecycleOwner) {
            val recViewPlacemark =
                requireActivity().findViewById<RecyclerView>(R.id.recViwPlaceMark)
            val adapterPlacemark = GenericRecyclerAdapter(it) { parent, viewId ->
                RecHldrPlacemark.from(parent, viewId)
            }
            recViewPlacemark.apply {
                adapter = adapterPlacemark
                layoutManager = LinearLayoutManager(requireContext())
                hasFixedSize()
            }
        }
    }
}