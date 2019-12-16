package restaurant.common.presentation.ui.view

import com.sha.bulletin.LoadingDialog
import restaurant.common.presentation.ui.activity.BaseActivity

interface LoadingIndicatable {
    fun activity(): BaseActivity?

    fun showLoading() = showLoading(LoadingDialog.Options.defaultOptions())

    fun showLoading(options: LoadingDialog.Options = LoadingDialog.Options.defaultOptions()) {
        activity()?.run { LoadingDialog.show(this) }
    }

    fun hideLoading() = LoadingDialog.dismiss()
}