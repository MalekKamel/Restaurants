package restaurant.common.presentation.ui.vm

import androidx.lifecycle.ViewModel
import com.sha.rxrequester.Presentable
import com.sha.rxrequester.RxRequester
import io.reactivex.disposables.CompositeDisposable
import restaurant.common.presentation.rx.ServerErrorHandler
import restaurant.common.presentation.rx.TokenExpiredHandler
import restaurant.common.presentation.rx.IoExceptionHandler
import restaurant.common.presentation.rx.NoSuchElementHandler
import restaurant.common.presentation.rx.OutOfMemoryErrorHandler
import restaurant.common.presentation.rx.ErrorContract
import restaurant.common.presentation.ui.view.BaseView
import restaurant.common.presentation.R
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

    private fun setupRequester(): RxRequester {
        val presentable = object: Presentable {
            override fun showError(error: String) {
                view.showErrorInFlashBar(error)
            }

            override fun showError(error: Int) {
                view.showErrorInFlashBar(error)
            }

            override fun showLoading() {
                view.showLoading()
            }

            override fun hideLoading() {
                view.hideLoading()
            }

            override fun onHandleErrorFailed() {
                view.showErrorInFlashBar(R.string.oops_something_went_wrong)
            }

        }

       val requester = RxRequester.create(ErrorContract::class.java, presentable)

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

