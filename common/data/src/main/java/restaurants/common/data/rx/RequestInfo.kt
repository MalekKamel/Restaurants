package restaurants.common.data.rx

import retrofit2.HttpException

data class RequestInfo(
        var inlineHandling: ((HttpException) -> Boolean)? = null,
        var retryCallback: () -> Unit = {},
        var showLoading: Boolean = true
) {

    fun inlineHandling(inlineHandling: ((HttpException) -> Boolean)?): RequestInfo {
        this.inlineHandling = inlineHandling
        return this
    }

    fun setRetryCallback(retryCallback: () -> Unit): RequestInfo {
        this.retryCallback = retryCallback
        return this
    }

}
