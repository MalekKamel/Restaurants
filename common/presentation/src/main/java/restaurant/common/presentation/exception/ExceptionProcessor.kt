package restaurant.common.presentation.exception

import android.text.TextUtils
import com.google.gson.GsonBuilder
import restaurant.common.presentation.exception.handler.http.HttpExceptionInfo
import restaurant.common.presentation.exception.handler.nonhttp.NonHttpExceptionInfo
import restaurant.common.presentation.rx.RxRequester
import restaurants.common.core.util.CrashlyticsUtil
import retrofit2.HttpException

/**
 * Created by Sha on 10/9/17.
 */

object ExceptionProcessor{

    internal fun process(throwable: Throwable, presenter: ExceptionPresenter) {
        try {

            if (throwable is HttpException) {
                handleHttpException(throwable, presenter)
                return
            }

            handleNonHttpException(throwable, presenter)

        } catch (e: Exception) {
            e.printStackTrace()
            // Retrofit throws an exception
            unknownException(presenter, throwable)
        }

    }

    @Throws(Exception::class)
    private fun handleHttpException(
            throwable: Throwable,
            presenter: ExceptionPresenter
    ) {
        val httpException = throwable as HttpException

        val body = HttpExceptionUtil.error(httpException)
        val code = HttpExceptionUtil.code(httpException)

        if (code == null) {
            unknownException(presenter, throwable)
            return
        }

        val optHandler = RxRequester.httpHandlers.firstOrNull { it.canHandle(code) }

        if (optHandler == null) {
            showOriginalHttpMessage(body, presenter, throwable)
            return
        }

        val info = HttpExceptionInfo(
                throwable = throwable,
                presenter = presenter,
                errorBody = body,
                code = code
        )

        optHandler.handle(info)

    }

    private fun showOriginalHttpMessage(
            body: String,
            presenter: ExceptionPresenter,
            throwable: Throwable
    ) {
        val contract = parseHttpExceptionModel(body)

        if (TextUtils.isEmpty(contract.message)) {
            unknownException(presenter, throwable)
            return
        }

        presenter.showError(contract.message)
    }

    private fun parseHttpExceptionModel(body: String): HttpExceptionContract {
        return GsonBuilder().create().fromJson(body, HttpExceptionContract::class.java)
    }

    private fun handleNonHttpException(
            throwable: Throwable,
            presenter: ExceptionPresenter
    ) {
        val optHandler = RxRequester.nonHttpHandlers.firstOrNull { it.canHandle(throwable) }

        if (optHandler == null) {
            unknownException(presenter, throwable)
            return
        }

        val info = NonHttpExceptionInfo()
                .setPresenter(presenter)
                .setThrowable(throwable)

        optHandler.handle(info)
    }

    private fun unknownException(presenter: ExceptionPresenter, throwable: Throwable) {
        // Default handling, show generic problem.
        presenter.onHandleFail()

        // Report Crashlytics to handle this exception in the future
        CrashlyticsUtil.log(throwable)
    }

}
