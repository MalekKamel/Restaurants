package restaurants.feature.restaurants

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_restaurant.view.*
import restaurant.common.presentation.ui.adapter.BaseRecyclerAdapter
import restaurant.common.presentation.ui.adapter.BaseViewHolder
import restaurant.common.presentation.ui.view.ViewInterface
import restaurants.common.core.picasso.PicassoUtil
import restaurants.common.data.model.Restaurant


/**
 * Created by Sha on 4/20/17.
 */

class RestaurantsAdapter(
        list: List<Restaurant>,
        viewInterface: ViewInterface
) : BaseRecyclerAdapter<Restaurant, RestaurantsAdapter.Vh>(list, viewInterface) {

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): Vh {
        return Vh(viewGroup)
    }

    inner class Vh internal constructor(viewGroup: ViewGroup)
        : BaseViewHolder<Restaurant>(viewGroup, R.layout.item_restaurant) {


        override fun bindView(item: Restaurant) {
            itemView.tvName.text = item.name
            itemView.tvCity.text = item.city
            PicassoUtil.load(
                    iv =itemView.ivPoster,
                    url = item.poster
            )
        }

    }
}
