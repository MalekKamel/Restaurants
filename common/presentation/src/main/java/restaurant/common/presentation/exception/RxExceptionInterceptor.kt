package restaurant.common.presentation.exception


import io.reactivex.functions.Consumer
import retrofit2.HttpException

/**
 * Created by Mickey on 4/1/17.
 */

class RxExceptionInterceptor(private val presenter: ExceptionPresenter) : Consumer<Throwable> {

    override fun accept(throwable: Throwable) {
        throwable.printStackTrace()
        presenter.hideLoading()

        // inline handling of the error
        if (presenter.requestInfo.inlineHandling != null &&
                presenter.requestInfo.inlineHandling!!(throwable))
            return

        ExceptionProcessor.process(throwable, presenter)
    }


}