package restaurant.common.presentation.exception.handler.nonhttp

import restaurants.common.core.R

class OutOfMemoryErrorHandler : NonHttpExceptionHandler() {

    override fun supportedThrowables(): List<Class<*>> {
        return listOf<Class<*>>(OutOfMemoryError::class.java)
    }

    override fun handle() {
        presenter.view.showErrorInFlashBar(R.string.no_memory_free_up_space)

    }
}
