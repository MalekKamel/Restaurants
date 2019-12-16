package restaurants.feature.splash.di

import org.koin.core.context.loadKoinModules
import restaurants.feature.splash.splashModule

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(splashModule)
}
