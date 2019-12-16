package restaurants.feature.restaurants

import com.sha.modelmapper.ListMapper
import com.sha.rxrequester.RequestOptions
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.data.DataManager
import restaurants.common.data.model.Restaurant
import restaurants.common.core.util.disposeBy
import restaurants.common.data.model.RestaurantMapper

val searchModule = module {
    viewModel { RestaurantsVm(get()) }
}

class RestaurantsVm(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun restaurants(callback: (List<Restaurant>) -> Unit) {
        val requestOptions = RequestOptions.Builder()
                .showLoading(true)
                .inlineErrorHandling { false }
                .build()
        requester.request(requestOptions) { dm.restaurantsRepo.all() }
                .subscribe {
                    val list =  ListMapper(RestaurantMapper()).map(it.restaurants)
                    callback(list)
                }.disposeBy(disposable = disposables)
    }

}
