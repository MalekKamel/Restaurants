package restaurants.common.data

import restaurants.common.data.pref.SharedPref
import restaurants.common.data.domain.restaurant.RestaurantsRepo

open class DataManager(
        val pref: SharedPref,
        val restaurantsRepo: RestaurantsRepo
)
