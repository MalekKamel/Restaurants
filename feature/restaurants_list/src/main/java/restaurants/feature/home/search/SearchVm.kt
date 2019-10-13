package restaurants.feature.home.search

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurant.common.presentation.DataManager
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurant.common.presentation.exception.disposeBy
import restaurants.common.data.model.Restaurant
import restaurants.common.data.model.toPresentation

val searchModule = module {
    viewModel { SearchVm(get()) }
}

class SearchVm(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun isValidSearchString(query: String): Boolean {
        return query.isNotEmpty()
    }

    fun restaurants(callback: (MutableList<Restaurant>) -> Unit) {
        request {
            dm.restaurantsRepo.all()
        }.subscribe {
            callback(it.restaurants.toPresentation())
        }.disposeBy(disposable = disposables)
    }

}

