package com.restaurants.app.navhost.app

import android.preference.PreferenceManager
import com.restaurants.app.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import restaurant.common.presentation.DataManager
import restaurants.common.data.rx.SchedulerProvider
import restaurants.common.data.rx.SchedulerProviderImpl
import restaurants.common.data.pref.SharedPref
import restaurants.common.data.network.api.ApiInterface
import restaurants.common.data.network.interceptor.TokenInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    // ApiInterface
    single {

        Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(ApiInterface::class.java)
    }

    // OkHttpClient
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val builder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(get<TokenInterceptor>())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
        builder.build()
    }

    single { DataManager(get(), get(), get()) }

    single { TokenInterceptor(get()) }

    single { SharedPref(get()) }

    // default pref
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

    single<SchedulerProvider> { SchedulerProviderImpl() }

}
