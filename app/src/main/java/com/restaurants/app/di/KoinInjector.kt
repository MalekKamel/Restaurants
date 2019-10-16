package com.restaurants.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import restaurants.common.data.di.injectPresentationModule

object KoinInjector {

    fun inject(context: Context){
        startKoin {
            androidContext(context)
        }
        injectPresentationModule()
    }

}