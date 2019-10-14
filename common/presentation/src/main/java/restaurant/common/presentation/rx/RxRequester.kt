package restaurant.common.presentation.rx

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import restaurant.common.presentation.exception.*
import restaurant.common.presentation.ui.view.BaseView
import restaurants.common.data.rx.RequestInfo

typealias Request<T> = () -> Flowable<T>

class RxRequester(
        val showError: (String) -> Unit,
        val showErrorRes: (Int) -> Unit,
        val showLoading: () -> Unit,
        val hideLoading: () -> Unit
) {

    lateinit var view: BaseView
    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * this function creates a new PublishProcessor and return it to the caller
     * then runs doRequest() which runs the request and publishes the result
     * to PublishProcessor.
     * @param requestInfo options for calling the request.
     * @param request callback for the request.
     */
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

    /**
     * this function runs the request and publishes the result to PublishProcessor which in turn
     * returns the result to the caller in case of success.
     * If any error occurred, it will be handled by handler classes.
     * You can invoke this function any number of times for retrying the current request,
     * for example, when you receive 401 error you can refresh the token in TokenExpiredHandler
     * then call this function to retry the request again after refreshing the token.
     * @param requestInfo options for calling the request
     * @param request callback for the request.
     * @param ps PublishProcessor to be called after success.
     */
    private fun <T> doRequest(
            requestInfo: RequestInfo?,
            request: Request<T>,
            ps: PublishProcessor<T>
    ) {
        val retryCallback = { doRequest(requestInfo, request, ps) }

        val info = requestInfo?.setRetryCallback(retryCallback)
                ?: RequestInfo(retryCallback = retryCallback)

        Flowable.fromPublisher(request())
                .addSchedulers()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(rxExceptionInterceptor(info.setRetryCallback { doRequest(info, request, ps) }))
                .onErrorResumeNext(Flowable.empty<T>())
                .subscribe { response ->
                    loading(false)
                    ps.onNext(response)
                    ps.onComplete()
                }.disposeBy(disposables)
    }

    private fun rxExceptionInterceptor(info: RequestInfo): RxExceptionInterceptor {
        val presenter = ExceptionPresenter(
                requestInfo = info,
                requester = this,
                showError = showError,
                showErrorRes = showErrorRes,
                showLoading = showLoading,
                hideLoading = hideLoading
        )
        return RxExceptionInterceptor(presenter)
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
            return
        }
        hideLoading()
    }

    fun dispose() {
        disposables.dispose()
    }
}