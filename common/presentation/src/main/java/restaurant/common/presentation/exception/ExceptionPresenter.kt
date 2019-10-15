package restaurant.common.presentation.exception

import restaurant.common.presentation.rx.RxRequester
import restaurants.common.data.rx.RequestInfo

/**
 * Created by Sha on 10/9/17.
 */

data class ExceptionPresenter(
        val requestInfo: RequestInfo,
        val requester: RxRequester,
        val showError: (String) -> Unit,
        val showErrorRes: (Int) -> Unit,
        val showLoading: () -> Unit,
        val hideLoading: () -> Unit,
        val onHandleFail: () -> Unit
) {
    fun retryRequest() {
        requestInfo.retryCallback()
    }
}