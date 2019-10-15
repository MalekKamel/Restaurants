package restaurant.common.presentation.ui.vm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import restaurant.common.presentation.exception.handler.http.ServerErrorHandler
import restaurant.common.presentation.exception.handler.http.TokenExpiredHandler
import restaurant.common.presentation.exception.handler.nonhttp.IoExceptionHandler
import restaurant.common.presentation.exception.handler.nonhttp.NoSuchElementHandler
import restaurant.common.presentation.exception.handler.nonhttp.OutOfMemoryErrorHandler
import restaurant.common.presentation.rx.RxRequester
import restaurant.common.presentation.ui.view.BaseView
import restaurants.common.core.R
import restaurants.common.data.DataManager
import restaurants.common.data.pref.SharedPref

open class BaseViewModel(val dm: DataManager)
    : ViewModel() {

    lateinit var view: BaseView
    var pref: SharedPref = dm.pref
    val disposables: CompositeDisposable = CompositeDisposable()
    var requester: RxRequester

    init {
        requester = setupRequester()
    }

    private fun setupRequester(): RxRequester{
       val requester = RxRequester(
                showError =    { view.showErrorInFlashBar(it) },
                showErrorRes = { view.showErrorInFlashBar(it) },
                showLoading =  { view.showLoading() },
                hideLoading =  { view.hideLoading() },
                onHandleFail = { view.showErrorInFlashBar(R.string.oops_something_went_wrong) }
        )
        if (RxRequester.nonHttpHandlers.isEmpty())
            RxRequester.nonHttpHandlers = listOf(
                    IoExceptionHandler(),
                    NoSuchElementHandler(),
                    OutOfMemoryErrorHandler()
            )
        if (RxRequester.httpHandlers.isEmpty())
            RxRequester.httpHandlers = listOf(
                    TokenExpiredHandler(),
                    ServerErrorHandler()
            )
        return requester
    }

    override fun onCleared() {
        disposables.dispose()
        requester.dispose()
        super.onCleared()
    }

}

