package com.sha.rxrequester

import com.sha.rxrequester.exception.ErrorMessage
import com.sha.rxrequester.exception.InterceptorArgs
import com.sha.rxrequester.exception.RxExceptionInterceptor
import com.sha.rxrequester.exception.handler.http.HttpExceptionHandler
import com.sha.rxrequester.exception.handler.nonhttp.NonHttpExceptionHandler
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor

typealias Request<T> = () -> Flowable<T>

class RxRequester private constructor(
        private val serverErrorContract: Class<*>,
        private val presentable: Presentable
){
    private val disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        var httpHandlers = listOf<HttpExceptionHandler>()
        var nonHttpHandlers = listOf<NonHttpExceptionHandler>()

        fun <T: ErrorMessage> create(
                serverErrorContract: Class<T>,
                presentable: Presentable): RxRequester {
            return RxRequester(serverErrorContract, presentable)
        }
    }

    /**
     * utility method to support Java overloading
     */
    fun <T> request(request: Request<T>): Flowable<T> {
        return request(RequestInfo.defaultInfo(), request)
    }

    /**
     * this function creates a new PublishProcessor and return it to the caller
     * then runs doRequest() which runs the request and publishes the result
     * to PublishProcessor.
     * @param requestInfo options for calling the request.
     * @param request callback for the request.
     */
    fun <T> request(
            requestInfo: RequestInfo = RequestInfo.defaultInfo(),
            request: Request<T>
    ): Flowable<T> {
        if (requestInfo.showLoading)
            presentable.showLoading()

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
     * @param requestInfo options for calling the request
     * @param request callback for the request.
     * @param ps PublishProcessor to be called after success.
     */
    private fun <T> doRequest(
            requestInfo: RequestInfo,
            request: Request<T>,
            ps: PublishProcessor<T>
    ) {
        val args = InterceptorArgs(
                presentable = presentable,
                serverErrorContract = serverErrorContract,
                inlineHandling = requestInfo.inlineHandling,
                retryRequest = { doRequest(requestInfo, request, ps) }
        )

        Flowable.fromPublisher(request())
                .subscribeOn(requestInfo.subscribeOnScheduler)
                .observeOn(requestInfo.observeOnScheduler)
                .doOnError(RxExceptionInterceptor(args))
                .onErrorResumeNext(Flowable.empty<T>())
                .subscribe {
                    ps.onNext(it)
                    ps.onComplete()
                    presentable.hideLoading()
                }.disposeBy(disposables)
    }

    fun dispose() {
        disposables.dispose()
    }
}

fun Disposable.disposeBy(disposable: CompositeDisposable) {
    disposable.add(this)
}