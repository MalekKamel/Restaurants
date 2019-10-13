package restaurants.common.core.koin

import android.content.Context

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class KoinHelper {
    companion object {
        fun start(context: Context){
            startKoin {
                androidContext(context)
            }
        }

        fun loadModules(modules: List<Module>) {
            loadKoinModules(modules)
        }
    }
}