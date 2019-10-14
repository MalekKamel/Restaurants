package restaurant.common.presentation.exception.handler.http

import restaurant.common.presentation.exception.ExceptionPresenter
import restaurant.common.presentation.ui.view.BaseView

/**
 * Created by Sha on 10/9/17.
 */

abstract class HttpExceptionHandler {

    protected lateinit var info: HttpExceptionInfo
    protected lateinit var throwable: Throwable
    protected lateinit var presenter: ExceptionPresenter

    protected abstract fun handle()
    protected abstract fun supportedExceptions(): List<Int>

    fun canHandle(code: Int): Boolean {
        return supportedExceptions().any { item -> code == item }
    }

    fun handle(info: HttpExceptionInfo) {
        this.info = info
        this.throwable = info.throwable
        this.presenter = info.presenter
        handle()
    }


}
