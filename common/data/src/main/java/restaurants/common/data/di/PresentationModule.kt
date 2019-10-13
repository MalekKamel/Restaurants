package restaurants.common.data.di

import org.koin.dsl.module
import restaurants.common.data.restaurant.RestaurantDataSrc
import restaurants.common.data.restaurant.RestaurantsRepo

val presentationModule = module {
    factory { RestaurantsRepo(get()) }
    factory { RestaurantDataSrc(get()) }
}
