package restaurant.common.presentation.ui.vm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import restaurant.common.presentation.DataManager
import restaurant.common.presentation.exception.*
import restaurant.common.presentation.ui.view.BaseView
import restaurants.common.data.pref.SharedPref
import restaurants.common.data.rx.RequestInfo

open class BaseViewModel(val dm: DataManager)
    : ViewModel() {

    lateinit var view: BaseView
    var pref: SharedPref = dm.pref
//    var restaurants: RestaurantsRepository = dm.restaurants

    val disposables: CompositeDisposable = CompositeDisposable()

    protected fun activity(): FragmentActivity? {
        return view.activity()
    }

    protected fun fragment(): Fragment? {
        return view.fragment()
    }


    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun loading(isLoading: Boolean) {
        if (isLoading) {
            view.showLoadingDialog()
            return
        }
        view.hideLoading()
    }

    fun <T> request(requestInfo: RequestInfo? = null, request: Request<T>): Flowable<T> {
        if (requestInfo?.showLoading != false)
            loading(true)

        val ps = PublishProcessor.create<T>()
        try {
            return ps
        } finally {
            doRequest(requestInfo, request, ps)
        }
    }

    private fun <T> doRequest(
            requestInfo: RequestInfo?,
            request: Request<T>,
            ps: PublishProcessor<T>
    ) {
        val info = requestInfo?.setRetryCallback { doRequest(requestInfo, request, ps) }
                ?: RequestInfo(
                        retryCallback = { doRequest(requestInfo, request, ps) }
                )

        Flowable.fromPublisher(request.invoke())
                .addSchedulers()
                .compose(FlowableUtil.handleException(rxError(
                        info.setRetryCallback { doRequest(info, request, ps) }
                )))
                .subscribe { response ->
                    loading(false)
                    ps.onNext(response)
                    ps.onComplete()
                }.disposeBy(disposables)
    }

    fun rxError(retryCallback: () -> Unit): RxExceptionInterceptor {
        return rxError(RequestInfo(
                retryCallback = retryCallback
        ))
    }

    private fun rxError(info: RequestInfo): RxExceptionInterceptor {
        val presenter = ExceptionPresenter(info)
                .setView(view)
                .setShowMessageInFlashBar {
                    loading(false)
                    view.showMessageInFlashBar(it)
                }
        return RxExceptionInterceptor(presenter)
    }


}

typealias Request<T> = () -> Flowable<T>