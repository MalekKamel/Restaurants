package restaurant.common.presentation.exception.handler.nonhttp

import okhttp3.internal.http2.ConnectionShutdownException
import restaurants.common.core.R
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Sha on 10/9/17.
 */

class IoExceptionHandler : NonHttpExceptionHandler() {

    override fun supportedThrowables(): List<Class<*>> {
        return listOf<Class<*>>(IOException::class.java)
    }

    override fun handle(info: NonHttpExceptionInfo) {

        if (info.throwable is SocketTimeoutException) {
            info.presenter.showErrorRes(R.string.socket_timeout_exception)
            return
        }

        if (info.throwable is UnknownHostException) {
            info.presenter.showErrorRes(R.string.offline_internet)
            return
        }

        if (info.throwable is ConnectionShutdownException) {
            // show nothing
            return
        }

        info.presenter.showErrorRes(R.string.offline_internet)
    }
}
