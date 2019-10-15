package restaurants.feature.home.restaurants

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurant.common.presentation.exception.disposeBy
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.data.DataManager
import restaurants.common.data.model.Restaurant
import restaurants.common.data.model.toPresentation

val searchModule = module {
    viewModel { RestaurantsVm(get()) }
}

class RestaurantsVm(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun isValidSearchString(query: String): Boolean {
        return query.isNotEmpty()
    }

    fun restaurants(callback: (MutableList<Restaurant>) -> Unit) {
        requester.request { dm.restaurantsRepo.all() }
                .subscribe {
                    callback(it.restaurants.toPresentation())
                }.disposeBy(disposable = disposables)
    }

}

