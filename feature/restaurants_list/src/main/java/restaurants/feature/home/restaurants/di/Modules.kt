package restaurants.feature.home.restaurants.di

import org.koin.core.context.loadKoinModules
import restaurants.feature.home.restaurants.searchModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            searchModule
    )
}
