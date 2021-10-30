package deghat.farhad.carlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import deghat.farhad.carlist.BR
import deghat.farhad.carlist.R

class FragPlacemarkList : Fragment() {

    private val viewModel: ViwMdlPlacemarkList by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
}