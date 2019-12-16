package restaurant.common.presentation.ui.view

import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.dialog.LoadingDialog
import restaurant.common.presentation.ui.dialog.LoadingDialogHelper
import restaurant.common.presentation.ui.dialog.info.InfoDialogHelper
import restaurants.common.core.util.ThreadHelper

interface ActivityIndicatable {
    fun activity(): BaseActivity?

    fun showLoading() {
        ThreadHelper.runOnUiThread {
            if (activity() == null) return@runOnUiThread
            hideLoading()
            val dialog = LoadingDialog.newInstance()
            dialog.show(activity()!!)
            LoadingDialogHelper.add(dialog)
        }
    }

    fun hideLoading() = LoadingDialogHelper.hide()

    fun hideDialogs() {
        ThreadHelper.runOnUiThread {
            LoadingDialogHelper.instances.forEach { it?.dismiss() }
            InfoDialogHelper.instances.forEach { it?.dismiss() }
        }
    }
}