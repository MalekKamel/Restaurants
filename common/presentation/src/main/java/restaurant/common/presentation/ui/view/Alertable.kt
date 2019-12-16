package restaurant.common.presentation.ui.view

import android.content.Context
import android.widget.Toast
import restaurant.common.presentation.ui.StatusItem
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.dialog.RetryDialogFrag
import restaurant.common.presentation.ui.dialog.InfoDialog
import restaurants.common.core.util.FlashbarHelper

interface Alertable {
    fun activity(): BaseActivity?
    fun context(): Context? = activity()

    fun showRetryDialog(message: String, options: RetryDialogFrag.Options = RetryDialogFrag.Options.defaultOptions()) {
        activity()?.run {
            options.message = message
            RetryDialogFrag.options = options
            RetryDialogFrag.show(this)
        }
    }

    fun toast(resId: Int) = Toast.makeText(context(), resId, Toast.LENGTH_LONG).show()

    fun showErrorInFlashBar(msg: String) = showMessageInFlashBar(StatusItem(StatusItem.ERROR, msg))

    fun showErrorInFlashBar(msgRes: Int) = showErrorInFlashBar(context()!!.getString(msgRes))

    fun showMessageInFlashBar(status: StatusItem) {
        activity()?.run { FlashbarHelper.show(status.statusMessage, status.getStatusColor(), this) }
    }

    fun showSuccessInFlashBar(msg: String) = showMessageInFlashBar(StatusItem(StatusItem.SUCCESS, msg))
    fun showSuccessInFlashBar(msgRes: Int) = showSuccessInFlashBar(context()!!.getString(msgRes))

    private fun showInfoDialog(options: InfoDialog.Options = InfoDialog.Options.defaultOptions()) {
        if(options.message == null) return
        activity()?.run {
            InfoDialog.options = options
            InfoDialog.show(this)
        }
    }

    fun showWarningDialog(msg: String?) {
        showInfoDialog(InfoDialog.Options.create(InfoDialog.MessageType.WARNING) { message = msg })
    }

    fun showWarningDialog(msgRes: Int) = showWarningDialog(context()?.getString(msgRes))

    fun showMessageDialog(msg: String?) {
        showInfoDialog(InfoDialog.Options.create(InfoDialog.MessageType.INFO) { message = msg })
    }

    fun showMessageDialog(msgRes: Int) {
        showInfoDialog(InfoDialog.Options.create(InfoDialog.MessageType.INFO) { message = context()?.getString(msgRes) })
    }

    fun showErrorDialog(errorRes: Int) = showErrorDialog(context()?.getString(errorRes))

    fun showErrorDialog(error: String?) {
        showInfoDialog(InfoDialog.Options.create(InfoDialog.MessageType.EXCEPTION) { message = error })
    }
}