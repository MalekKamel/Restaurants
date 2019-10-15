package restaurant.common.presentation.exception.handler.http

/**
 * Created by Sha on 10/9/17.
 */

class TokenExpiredHandler : HttpExceptionHandler() {

    override fun supportedExceptions(): List<Int> {
        return listOf(401)
    }

    override fun handle(info: HttpExceptionInfo) {
//        presenter.requester.request<*> { /* call refresh token request here */ }
//                .subscribe {
//                    presenter.retryRequest()
//                }
    }

}
