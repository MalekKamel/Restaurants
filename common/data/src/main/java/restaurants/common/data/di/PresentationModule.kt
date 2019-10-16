package restaurants.common.data.di

import android.preference.PreferenceManager
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import restaurants.common.data.BuildConfig
import restaurants.common.data.DataManager
import restaurants.common.data.network.api.ApiInterface
import restaurants.common.data.network.interceptor.TokenInterceptor
import restaurants.common.data.pref.SharedPref
import restaurants.common.data.restaurant.RestaurantDataSrc
import restaurants.common.data.restaurant.RestaurantsRepo
import restaurants.common.data.rx.SchedulerProvider
import restaurants.common.data.rx.SchedulerProviderImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun injectPresentationModule() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
            presentationModule
    )
}

val presentationModule = module {
    factory { RestaurantsRepo(get()) }
    factory { RestaurantDataSrc(get()) }

    single { DataManager(get(), get()) }
    single { TokenInterceptor(get()) }
    single { SharedPref(get()) }
    single<SchedulerProvider> { SchedulerProviderImpl() }

    // default pref
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

    // OkHttpClient
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val builder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(get<TokenInterceptor>())
        builder.build()
    }

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

}
