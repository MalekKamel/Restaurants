package com.restaurants.app.navhost.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import com.restaurants.app.R
import com.sha.kamel.navigator.NavigatorOptions
import org.koin.core.context.loadKoinModules
import restaurants.common.core.koin.KoinHelper
import restaurants.common.core.util.CrashlyticsUtil
import restaurants.common.data.di.presentationModule

/**
 * Created by Sha on 13/04/17.
 */

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        try {

            context = applicationContext

            KoinHelper.start(this)
            loadKoinModules(listOf(appModule, presentationModule))

            NavigatorOptions.instance().frameLayoutId = R.id.mainFrame

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
