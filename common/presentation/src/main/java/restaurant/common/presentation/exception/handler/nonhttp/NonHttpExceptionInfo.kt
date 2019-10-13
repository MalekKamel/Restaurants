package restaurant.common.presentation.exception.handler.nonhttp

import restaurant.common.presentation.exception.ExceptionPresenter

class NonHttpExceptionInfo {
    lateinit var throwable: Throwable
    lateinit var presenter: ExceptionPresenter

    fun setThrowable(throwable: Throwable): NonHttpExceptionInfo {
        this.throwable = throwable
        return this
    }

    fun setPresenter(presenter: ExceptionPresenter): NonHttpExceptionInfo {
        this.presenter = presenter
        return this
    }
}
