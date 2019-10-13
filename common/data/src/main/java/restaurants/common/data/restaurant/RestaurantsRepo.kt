package restaurants.common.data.restaurant

import io.reactivex.Flowable
import restaurants.common.data.base.BaseRepo
import restaurants.common.data.model.RestaurantResponse

class RestaurantsRepo(private val restaurantDataSrc: RestaurantDataSrc): BaseRepo() {

    fun all(): Flowable<RestaurantResponse> {
        return restaurantDataSrc.all()
    }


}
