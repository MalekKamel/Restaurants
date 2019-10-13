package restaurant.common.presentation.exception.handler.http

import restaurants.common.core.R


class ServerErrorHandler : HttpExceptionHandler() {

    override fun supportedExceptions(): List<Int> {
        return listOf(500)
    }

    override fun handle() {
        presenter.view.showErrorInFlashBar(R.string.oops_something_went_wrong)
    }

}