package restaurants.common.data.restaurant

import io.reactivex.Flowable
import restaurants.common.data.model.RestaurantResponse
import restaurants.common.data.network.api.ApiInterface

class RestaurantDataSrc(private val api: ApiInterface) {

    fun all(): Flowable<RestaurantResponse> {
        return api.restaurants()
    }

}