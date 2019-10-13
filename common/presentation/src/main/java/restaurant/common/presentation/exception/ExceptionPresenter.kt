package restaurant.common.presentation.exception

import restaurant.common.presentation.ui.StatusItem
import restaurants.common.data.rx.RequestInfo

import restaurant.common.presentation.ui.view.BaseView

/**
 * Created by Sha on 10/9/17.
 */

class ExceptionPresenter(var requestInfo: RequestInfo) {
    lateinit var view: BaseView
    lateinit var showMessageInFlashBar: (StatusItem) -> Unit

    val retryCallback: () -> Unit
        get() = requestInfo.retryCallback

    fun setView(view: BaseView): ExceptionPresenter {
        this.view = view
        return this
    }

    fun setShowMessageInFlashBar(showMessageInFlashBar: (StatusItem) -> Unit): ExceptionPresenter {
        this.showMessageInFlashBar = showMessageInFlashBar
        return this
    }


}
