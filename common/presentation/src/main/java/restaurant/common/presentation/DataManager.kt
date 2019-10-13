package restaurant.common.presentation

import restaurants.common.data.rx.SchedulerProvider
import restaurants.common.data.pref.SharedPref
import restaurants.common.data.restaurant.RestaurantsRepo

open class DataManager(
        val restaurantsRepo: RestaurantsRepo,
        val pref: SharedPref,
        val schedulerProvider: SchedulerProvider
)
