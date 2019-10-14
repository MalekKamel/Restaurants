package restaurants.feature.home.restaurants

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import restaurant.common.presentation.ui.frag.BaseFrag
import restaurants.common.core.util.ThreadUtil
import org.koin.android.viewmodel.ext.android.viewModel
import restaurants.common.core.util.linearLayoutManager
import restaurants.feature.home.R
import restaurants.feature.home.restaurants.di.injectFeature


class RestaurantsFrag : BaseFrag<RestaurantsVm>() {

    override val vm: RestaurantsVm by viewModel()
    override var layoutId: Int = R.layout.frag_search
    override var hasSwipeRefresh: Boolean = true

    lateinit var rv: RecyclerView

    companion object {
        fun newInstance(): RestaurantsFrag {
            return RestaurantsFrag()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
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

    override fun showLoading() {
        ThreadUtil.runOnUiThread { activity!!.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).isRefreshing = true }
    }

    override fun hideLoading() {
        ThreadUtil.runOnUiThread { activity!!.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).isRefreshing = false }
    }

    override fun onSwipeRefresh() {
        loadRestaurants()
    }
}
