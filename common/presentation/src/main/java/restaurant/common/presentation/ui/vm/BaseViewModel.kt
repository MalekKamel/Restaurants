package restaurant.common.presentation.ui.vm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import restaurant.common.presentation.rx.RxRequester
import restaurant.common.presentation.ui.view.BaseView
import restaurants.common.data.DataManager
import restaurants.common.data.pref.SharedPref

open class BaseViewModel(val dm: DataManager)
    : ViewModel() {

    lateinit var view: BaseView
    var pref: SharedPref = dm.pref
    val disposables: CompositeDisposable = CompositeDisposable()
    var requester: RxRequester

    init {
        requester = RxRequester(
                showError = { view.showErrorInFlashBar(it) },
                showErrorRes = { view.showErrorInFlashBar(it) },
                showLoading = { view.showLoading() },
                hideLoading = { view.hideLoading() }
        )
    }

    override fun onCleared() {
        disposables.dispose()
        requester.dispose()
        super.onCleared()
    }

}

