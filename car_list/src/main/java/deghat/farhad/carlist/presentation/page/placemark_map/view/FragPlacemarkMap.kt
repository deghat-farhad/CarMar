package deghat.farhad.carlist.presentation.page.placemark_map.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import deghat.farhad.carlist.BR
import deghat.farhad.carlist.R
import deghat.farhad.carlist.presentation.page.placemark_list.viewmodel.ViwMdlPlacemarkList
import deghat.farhad.carlist.presentation.page.placemark_map.viewmodel.ViwMdlPlacemarkMap

@AndroidEntryPoint
class FragPlacemarkMap : Fragment() {

    private val viewModel: ViwMdlPlacemarkMap by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            lifecycleOwner = this@FragPlacemarkMap
        }.root
    }
}