package restaurant.common.presentation.exception.handler.nonhttp

/**
 * Created by Sha on 10/9/17.
 */

abstract class NonHttpExceptionHandler {

    protected abstract fun supportedThrowables(): List<Class<*>>

    fun canHandle(throwable: Throwable): Boolean {
        return supportedThrowables().any { t -> t.isAssignableFrom(throwable.javaClass) }
    }
    
    abstract fun handle(info: NonHttpExceptionInfo)


}
