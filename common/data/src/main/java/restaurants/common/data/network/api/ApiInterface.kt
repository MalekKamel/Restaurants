package restaurants.common.data.network.api

import restaurants.common.data.model.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("restaurants")
   suspend fun restaurants(@Query("country") country: String = "US"): RestaurantResponse
}