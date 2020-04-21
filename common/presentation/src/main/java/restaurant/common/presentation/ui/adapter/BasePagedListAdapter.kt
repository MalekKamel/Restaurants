package restaurant.common.presentation.ui.adapter

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import restaurant.common.presentation.ui.view.ViewInterface
import restaurants.common.core.util.reportAndPrint


abstract class BasePagedListAdapter<T> constructor(protected var viewInterface: ViewInterface, diffCallback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, BaseViewHolder<T>>(diffCallback) {


    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        try {
            val item = getItem(position)
            if( item != null) holder.bindView(item)
        } catch (e: Exception) {
            e.reportAndPrint()
        }

    }


}
