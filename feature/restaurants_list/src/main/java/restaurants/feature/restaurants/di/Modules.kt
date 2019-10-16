package restaurants.feature.restaurants.di

import org.koin.core.context.loadKoinModules
import restaurants.feature.restaurants.searchModule

fun injectRestaurantsListFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            searchModule
    )
}
