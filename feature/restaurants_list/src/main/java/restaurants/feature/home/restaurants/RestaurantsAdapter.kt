package restaurants.feature.home.restaurants

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_restaurant.view.*
import restaurants.feature.home.R
import restaurants.common.core.picasso.PicassoUtil
import restaurant.common.presentation.ui.view.BaseView
import restaurants.common.data.model.Restaurant


/**
 * Created by Sha on 4/20/17.
 */

class RestaurantsAdapter(
        list: MutableList<Restaurant>,
        baseView: BaseView
) : restaurant.common.presentation.ui.adapter.BaseRecyclerAdapter<Restaurant, RestaurantsAdapter.Vh>(list, baseView) {

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): Vh {
        return Vh(viewGroup)
    }

    inner class Vh internal constructor(viewGroup: ViewGroup)
        : restaurant.common.presentation.ui.adapter.BaseViewHolder<Restaurant>(viewGroup, R.layout.item_restaurant) {


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
