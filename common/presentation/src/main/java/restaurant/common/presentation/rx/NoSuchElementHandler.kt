package restaurant.common.presentation.rx

import com.sha.rxrequester.exception.handler.nonhttp.NonHttpExceptionHandler
import com.sha.rxrequester.exception.handler.nonhttp.NonHttpExceptionInfo
import restaurant.common.presentation.R
import java.util.*

class NoSuchElementHandler : NonHttpExceptionHandler<NoSuchElementException>() {

    override fun supportedThrowables(): List<Class<out NoSuchElementException>> {
        return listOf(NoSuchElementException::class.java)
    }

    override fun handle(info: NonHttpExceptionInfo) {
        info.presentable.showError(R.string.no_data_entered_yet)
    }
}
