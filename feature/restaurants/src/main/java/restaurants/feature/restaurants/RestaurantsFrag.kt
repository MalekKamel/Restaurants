package restaurants.feature.restaurants

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sha.bulletin.dialog.LoadingDialog
import kotlinx.android.synthetic.main.frag_restaurants.*
import org.koin.android.viewmodel.ext.android.viewModel
import restaurant.common.presentation.ui.frag.BaseFrag
import restaurants.common.core.util.linearLayoutManager
import restaurants.feature.restaurants.di.injectFeature

class RestaurantsFrag : BaseFrag<RestaurantsViewModel>() {
    override val vm: RestaurantsViewModel by viewModel()
    override var layoutId: Int = R.layout.frag_restaurants
    override var swipeRefreshLayoutId: Int = R.id.swipeRefresh

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
    }

    override fun doOnViewCreated() {
        super.doOnViewCreated()
        setupUi()
        observeVm()
        loadRestaurants()
    }

    private fun setupUi() {
        rv.linearLayoutManager(context)
    }

    private fun observeVm() {
        vm.restaurants.observe(this, Observer {
            rv.adapter = RestaurantsAdapter(list = it, viewInterface = this)
            rv.scheduleLayoutAnimation()
        })
    }

    private fun loadRestaurants() {
        vm.restaurants()
    }

    override fun onSwipeRefresh() { loadRestaurants() }

    override fun showLoadingDialog(content: String): LoadingDialog? {
        toggleRefresh(true)
        return null
    }

    override fun dismissLoadingDialogs() {
        toggleRefresh(false)
    }

    private fun toggleRefresh(show: Boolean) { swipeRefresh.isRefreshing = show }

}
