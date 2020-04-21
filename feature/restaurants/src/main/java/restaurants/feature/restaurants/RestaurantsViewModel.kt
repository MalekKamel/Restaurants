package restaurants.feature.restaurants

import androidx.lifecycle.MutableLiveData
import com.sha.modelmapper.ListMapper
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.data.DataManager
import restaurants.common.data.model.Restaurant
import restaurants.common.data.model.RestaurantMapper

val searchModule = module {
    viewModel { RestaurantsViewModel(get()) }
}

class RestaurantsViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {
    val restaurants = MutableLiveData<List<Restaurant>>()

    fun restaurants() {
        request {
            val response = dm.restaurantsRepo.all()
            restaurants.value = ListMapper(RestaurantMapper()).map(response.restaurants)
        }
    }

}
