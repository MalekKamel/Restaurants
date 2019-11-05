package restaurant.common.presentation.rx

import com.sha.rxrequester.exception.handler.throwable.ThrowableHandler
import com.sha.rxrequester.exception.handler.throwable.ThrowableInfo
import restaurant.common.presentation.R

class OutOfMemoryErrorHandler : ThrowableHandler<OutOfMemoryError>() {

    override fun supportedErrors(): List<Class<OutOfMemoryError>> {
        return listOf(OutOfMemoryError::class.java)
    }

    override fun handle(info: ThrowableInfo) {
        info.presentable.showError(R.string.no_memory_free_up_space)

    }
}
