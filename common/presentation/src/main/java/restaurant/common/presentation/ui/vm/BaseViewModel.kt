package restaurant.common.presentation.ui.vm

import androidx.lifecycle.ViewModel
import com.sha.rxrequester.Presentable
import com.sha.rxrequester.RxRequester
import io.reactivex.disposables.CompositeDisposable
import restaurant.common.presentation.rx.ServerErrorHandler
import restaurant.common.presentation.rx.IoExceptionHandler
import restaurant.common.presentation.rx.NoSuchElementHandler
import restaurant.common.presentation.rx.OutOfMemoryErrorHandler
import restaurant.common.presentation.rx.ErrorContract
import restaurant.common.presentation.ui.view.ViewInterface
import restaurant.common.presentation.R
import restaurants.common.data.DataManager
import restaurants.common.data.pref.SharedPref

open class BaseViewModel(val dm: DataManager)
    : ViewModel() {

    lateinit var viewInterface: ViewInterface
    var pref: SharedPref = dm.pref
    val disposables: CompositeDisposable = CompositeDisposable()
    var requester: RxRequester

    init {
        requester = setupRequester()
    }

    private fun setupRequester(): RxRequester {
        val presentable = object: Presentable {
            override fun showError(error: String) {
                viewInterface.showErrorInFlashBar(error)
            }

            override fun showError(error: Int) {
                viewInterface.showErrorInFlashBar(error)
            }

            override fun showLoading() {
                viewInterface.showLoading()
            }

            override fun hideLoading() {
                viewInterface.hideLoading()
            }

            override fun onHandleErrorFailed(throwable: Throwable) {
                viewInterface.showErrorInFlashBar(R.string.oops_something_went_wrong)
            }

        }

       val requester = RxRequester.create(ErrorContract::class.java, presentable)

        if (RxRequester.throwableHandlers.isEmpty())
            RxRequester.throwableHandlers = listOf(
                    IoExceptionHandler(),
                    NoSuchElementHandler(),
                    OutOfMemoryErrorHandler()
            )
        if (RxRequester.httpHandlers.isEmpty())
            RxRequester.httpHandlers = listOf(
                    ServerErrorHandler()
            )
        return requester
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}

