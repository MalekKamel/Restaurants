package restaurant.common.presentation.exception.handler.http

/**
 * Created by Sha on 10/9/17.
 */

class TokenExpiredHandler : HttpExceptionHandler() {

    override fun supportedExceptions(): List<Int> {
        return listOf(401)
    }

    override fun handle() {
//            view.baseViewModel()!!
//                    .refreshToken() {
//                       //  retry the current request
//                        presenter.retryCallback()
//                    }
    }

}
