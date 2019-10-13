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

    override fun handle() {

        if (throwable is SocketTimeoutException) {
            presenter.view.showErrorInFlashBar(R.string.socket_timeout_exception)
            return
        }

        if (throwable is UnknownHostException) {
            presenter.view.showErrorInFlashBar(R.string.offline_internet)
            return
        }

        if (throwable is ConnectionShutdownException) {
            // show nothing
            return
        }

        presenter.view.showErrorInFlashBar(R.string.offline_internet)
    }
}
