package restaurants.common.data.domain.restaurant

import restaurants.common.data.base.BaseRepo
import restaurants.common.data.model.RestaurantResponse

class RestaurantsRepo(private val restaurantDataSrc: RestaurantDataSrc): BaseRepo() {

   suspend fun all(): RestaurantResponse {
        return restaurantDataSrc.all()
    }

}
