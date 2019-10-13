package restaurant.common.presentation.exception


import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.java.KoinJavaComponent
import restaurants.common.data.rx.SchedulerProvider


object FlowableUtil {

    fun <T> mainObs(): FlowableTransformer<T, T> {
        return FlowableTransformer { it.observeOn(AndroidSchedulers.mainThread()) }
    }


    fun <T> handleException(interceptor: RxExceptionInterceptor): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it
                    .compose(mainObs<T>())
                    .doOnError(interceptor)
                    .onErrorResumeNext(Flowable.empty<T>())
        }
    }

}

fun Disposable.disposeBy(disposable: CompositeDisposable) {
    disposable.add(this)
}

fun  <T> Flowable<T>.addSchedulers(
        schedulerProvider: SchedulerProvider
        = KoinJavaComponent.get(SchedulerProvider::class.java)
): Flowable<T>{

    return subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
}