package restaurant.common.presentation.exception


import io.reactivex.functions.Consumer
import retrofit2.HttpException

/**
 * Created by Mickey on 4/1/17.
 */

class RxExceptionInterceptor(private val presenter: ExceptionPresenter) : Consumer<Throwable> {

    override fun accept(throwable: Throwable) {
        throwable.printStackTrace()
        presenter.view.hideLoading()

        // inline handling of the error
        if (presenter.requestInfo.inlineHandling != null &&
                presenter.requestInfo.inlineHandling!!.invoke(throwable as HttpException))
            return

        ExceptionProcessor.instance.process(throwable, presenter)
    }


}