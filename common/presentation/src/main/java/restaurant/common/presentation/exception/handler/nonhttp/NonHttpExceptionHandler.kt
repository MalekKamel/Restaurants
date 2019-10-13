package restaurant.common.presentation.exception.handler.nonhttp

import restaurant.common.presentation.exception.ExceptionPresenter
import restaurant.common.presentation.ui.view.BaseView

/**
 * Created by Sha on 10/9/17.
 */

abstract class NonHttpExceptionHandler {

    private lateinit var info: NonHttpExceptionInfo
    protected lateinit var view: BaseView
    protected lateinit var throwable: Throwable
    protected lateinit var presenter: ExceptionPresenter

    protected abstract fun supportedThrowables(): List<Class<*>>

    protected abstract fun handle()

    fun handle(info: NonHttpExceptionInfo) {
        this.info = info
        this.throwable = info.throwable
        this.view = info.presenter.view
        this.presenter = info.presenter
        handle()
    }

    fun canHandle(throwable: Throwable): Boolean {
        return supportedThrowables().any { t -> t.isAssignableFrom(throwable.javaClass) }
    }


}
