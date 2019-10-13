package restaurant.common.presentation.exception.handler.http

import restaurant.common.presentation.exception.ExceptionPresenter


data class HttpExceptionInfo (
        var throwable: Throwable,
        var presenter: ExceptionPresenter,
        var errorBody: String,
        var code: Int = 0
)
