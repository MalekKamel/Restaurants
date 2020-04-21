package restaurants.common.data.restaurant

import restaurants.common.data.model.RestaurantResponse
import restaurants.common.data.network.api.ApiInterface

class RestaurantDataSrc(private val api: ApiInterface) {

    suspend fun all(): RestaurantResponse {
        return api.restaurants()
    }

}