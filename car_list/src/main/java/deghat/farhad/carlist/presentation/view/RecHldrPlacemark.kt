package deghat.farhad.carlist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import deghat.farhad.carlist.presentation.item.RecItmPlacemark
import deghat.farhad.common.presentation.util.recycler_view.Holder
import deghat.farhad.common.presentation.util.recycler_view.Visitable

class RecHldrPlacemark(private val viewDataBinding: ViewDataBinding) : Holder(viewDataBinding) {

    companion object {
        fun from(parent: ViewGroup, viewId: Int): RecHldrPlacemark {
            val inflater = LayoutInflater.from(parent.context)
            val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                viewId,
                parent,
                false
            )
            return RecHldrPlacemark(viewDataBinding)
        }
    }

    override fun bind(item: Visitable) {
        when (item) {
            is RecItmPlacemark.Placemark -> viewDataBinding.setVariable(BR.item, item)
        }
    }
}