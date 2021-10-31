package deghat.farhad.carlist.presentation.page.placemark_list.view

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
import deghat.farhad.carlist.presentation.page.placemark_list.viewmodel.ViwMdlPlacemarkList
import deghat.farhad.common.presentation.UiState
import deghat.farhad.common.presentation.util.recycler_view.GenericRecyclerAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FragPlacemarkList : Fragment() {

    private val viewModel: ViwMdlPlacemarkList by viewModels()

    @Inject
    lateinit var stateHandlerFragPlacemarkList: StateHandlerFragPlacemarkList

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
            setVariable(BR.stateHandler, stateHandlerFragPlacemarkList)
            lifecycleOwner = this@FragPlacemarkList
        }.root
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
            stateHandlerFragPlacemarkList.setUiState(it)
            if (it is UiState.HasData) {
                val recViewPlacemark =
                    requireActivity().findViewById<RecyclerView>(R.id.recViwPlaceMark)
                val adapterPlacemark = GenericRecyclerAdapter(it.content) { parent, viewId ->
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
}