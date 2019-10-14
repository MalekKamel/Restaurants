package restaurants.common.data.rx

data class RequestInfo(
        var inlineHandling: ((Throwable) -> Boolean)? = null,
        var retryCallback: () -> Unit = {},
        var showLoading: Boolean = true
) {

    fun inlineHandling(inlineHandling: ((Throwable) -> Boolean)?): RequestInfo {
        this.inlineHandling = inlineHandling
        return this
    }

    fun setRetryCallback(retryCallback: () -> Unit): RequestInfo {
        this.retryCallback = retryCallback
        return this
    }

}
