package restaurants.feature.home.search.di

import org.koin.core.context.loadKoinModules
import restaurants.feature.home.search.searchModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            searchModule
    )
}
