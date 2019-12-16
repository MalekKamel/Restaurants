package restaurant.common.presentation.ui.view

import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.dialog.LoadingDialog

interface LoadingIndicatable {
    fun activity(): BaseActivity?

    fun showLoading() = showLoading(LoadingDialog.Options.defaultOptions())

    fun showLoading(options: LoadingDialog.Options = LoadingDialog.Options.defaultOptions()) {
        activity()?.run { LoadingDialog.show(this) }
    }

    fun hideLoading() = LoadingDialog.dismiss()
}