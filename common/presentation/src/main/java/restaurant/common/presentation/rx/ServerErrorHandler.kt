package restaurant.common.presentation.rx

import com.sha.rxrequester.exception.handler.http.HttpExceptionHandler
import com.sha.rxrequester.exception.handler.http.HttpExceptionInfo
import restaurant.common.presentation.R


class ServerErrorHandler : HttpExceptionHandler() {

    override fun supportedExceptions(): List<Int> {
        return listOf(500)
    }

    override fun handle(info: HttpExceptionInfo) {
        info.presentable.showError(R.string.oops_something_went_wrong)
    }

}