package deghat.farhad.common.presentation.util.recycler_view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class Holder
    (private val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    abstract fun bind(item: Visitable)
}