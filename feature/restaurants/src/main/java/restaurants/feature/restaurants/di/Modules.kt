package restaurants.feature.restaurants.di

import org.koin.core.context.loadKoinModules
import restaurants.feature.restaurants.searchModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(searchModule)
}
