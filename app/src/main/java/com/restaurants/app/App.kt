package com.restaurants.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import com.restaurants.app.di.KoinInjector
import com.sha.kamel.navigator.NavigatorOptions
import restaurants.common.core.util.CrashlyticsUtil

/**
 * Created by Sha on 13/04/17.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        try {

            context = applicationContext

            KoinInjector.inject(this)

            NavigatorOptions.frameLayoutId = R.id.mainFrame

        } catch (e: Exception) {
            CrashlyticsUtil.logAndPrint(e)
        }

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @JvmStatic
        fun string(@StringRes res: Int): String {
            return context.getString(res)
        }

    }
}
