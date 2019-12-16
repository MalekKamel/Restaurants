package restaurants.feature.restaurants

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.android.viewmodel.ext.android.viewModel
import restaurant.common.presentation.ui.frag.BaseFrag
import restaurants.common.core.util.linearLayoutManager
import restaurants.feature.restaurants.di.injectRestaurantsListFeature

class RestaurantsFrag : BaseFrag<RestaurantsViewModel>() {

    override val vm: RestaurantsViewModel by viewModel()
    override var layoutId: Int = R.layout.frag_restaurants
    override var swipeRefreshLayoutId: Int = R.id.swipeRefresh

    lateinit var rv: RecyclerView

    companion object {
        fun newInstance(): RestaurantsFrag {
            return RestaurantsFrag()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectRestaurantsListFeature()
        super.onCreate(savedInstanceState)
    }

    override fun doOnViewCreated() {
        super.doOnViewCreated()

        rv = findViewById(R.id.rv)
        rv.linearLayoutManager(context)

        loadRestaurants()
    }

    private fun loadRestaurants() {
        vm.restaurants {
            rv.adapter = RestaurantsAdapter(list = it, baseView = this)
            rv.scheduleLayoutAnimation()
        }
    }

    override fun onSwipeRefresh() {
        loadRestaurants()
    }

    override fun showLoading() {
        toggleRefresh(true)
    }

    override fun hideLoading() {
        toggleRefresh(false)
    }

    private fun toggleRefresh(show: Boolean) {
        activity!!.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).isRefreshing = show
    }

}
