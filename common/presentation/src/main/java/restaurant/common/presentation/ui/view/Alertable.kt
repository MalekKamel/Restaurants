package restaurant.common.presentation.ui.view

import android.content.Context
import android.widget.Toast
import com.sha.bulletin.InfoDialog
import com.sha.bulletin.InfoSheet
import com.sha.bulletin.RetryDialog
import restaurant.common.presentation.ui.StatusItem
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurants.common.core.util.FlashbarHelper

interface Alertable {
    fun activity(): BaseActivity?
    fun context(): Context? = activity()

    fun showRetryDialog(message: String,
                        options: RetryDialog.Options = RetryDialog.Options.defaultOptions()) {
        activity()?.run {
            options.message = message
            RetryDialog.options = options
            RetryDialog.show(this)
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

    /// >>>>>>>>>  INFO DIALOG

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

    /// >>>>>>>>>  INFO SHEET

    private fun showInfoSheet(options: InfoSheet.Options = InfoSheet.Options.defaultOptions()) {
        if(options.message == null) return
        activity()?.run {
            InfoSheet.options = options
            InfoSheet.show(this)
        }
    }
    fun showWarningSheet(msg: String?) {
        showInfoSheet(InfoSheet.Options.create(InfoSheet.MessageType.WARNING) { message = msg })
    }

    fun showWarningSheet(msgRes: Int) = showWarningSheet(context()?.getString(msgRes))

    fun showMessageSheet(msg: String?) {
        showInfoSheet(InfoSheet.Options.create(InfoSheet.MessageType.INFO) { message = msg })
    }

    fun showMessageSheet(msgRes: Int) {
        showInfoSheet(InfoSheet.Options.create(InfoSheet.MessageType.INFO) { message = context()?.getString(msgRes) })
    }

    fun showErrorSheet(errorRes: Int) = showErrorSheet(context()?.getString(errorRes))

    fun showErrorSheet(error: String?) {
        showInfoSheet(InfoSheet.Options.create(InfoSheet.MessageType.EXCEPTION) { message = error })
    }
}