package deghat.farhad.common.presentation.util.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericRecyclerAdapter<T : Collection<Visitable>, H : Holder>(
    private val itemList: T,
    private val holderBuilder: (ViewGroup, Int) -> H
) :
    RecyclerView.Adapter<H>() {

    override fun getItemViewType(position: Int): Int {
        return itemList.elementAt(position).type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return holderBuilder(parent, viewType)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(itemList.elementAt(position))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}