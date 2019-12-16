package restaurant.common.presentation.ui.view

import android.content.Context
import android.widget.Toast
import restaurant.common.presentation.ui.StatusItem
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.dialog.RetryDialogFrag
import restaurant.common.presentation.ui.dialog.info.InfoDialog
import restaurant.common.presentation.ui.dialog.info.InfoDialogHelper
import restaurants.common.core.util.FlashbarUtil
import restaurants.common.core.util.ThreadHelper

interface Alertable {
    fun activity(): BaseActivity?

    fun context(): Context? = activity()

    fun showRetryDialog(retryCallback: () -> Unit, closeCallback: () -> Unit) {
        ThreadHelper.runOnUiThread {
            RetryDialogFrag.newInstance(
                    retryCallback,
                    closeCallback).show(activity()!!)
        }
    }

    fun showRetryDialog(message: String, retryCallback: () -> Unit, closeCallback: () -> Unit) {
        ThreadHelper.runOnUiThread {
            RetryDialogFrag.newInstance(
                    message,
                    retryCallback,
                    closeCallback).show(activity()!!)
        }
    }

    private fun showInfoDialog(msg: String?, type: InfoDialog.MessageType) {
        ThreadHelper.runOnUiThread {
            if (msg == null) return@runOnUiThread
            val infoDialog = InfoDialog.newInstance(type, msg, false, null)
            infoDialog.show(activity()!!)
            InfoDialogHelper.add(infoDialog)
        }
    }

    fun showWarningDialog(msg: String?) = showInfoDialog(type = InfoDialog.MessageType.WARNING, msg = msg)

    fun toast(resId: Int) = Toast.makeText(context(), resId, Toast.LENGTH_LONG).show()

    fun showErrorInFlashBar(msg: String) = showMessageInFlashBar(StatusItem(StatusItem.ERROR, msg))

    fun showErrorInFlashBar(msgRes: Int) = showErrorInFlashBar(context()!!.getString(msgRes))

    fun showMessageInFlashBar(status: StatusItem) {
        ThreadHelper.runOnUiThread {
            if (activity() == null) return@runOnUiThread

            FlashbarUtil.show(
                    status.statusMessage,
                    status.getStatusColor(),
                    activity()!!
            )
        }
    }

    fun showSuccessInFlashBar(msg: String) = showMessageInFlashBar(StatusItem(StatusItem.SUCCESS, msg))

    fun showSuccessInFlashBar(msgRes: Int) = showSuccessInFlashBar(context()!!.getString(msgRes))

    fun showWarningDialog(msgRes: Int) = showWarningDialog(context()?.getString(msgRes))

    fun showErrorDialog(errorRes: Int) = showErrorDialog(context()?.getString(errorRes))

    fun showMessageDialog(msg: String?) = showMessageDialog(msg, null)

    fun showMessageDialog(msg: String?, closeCallback: (() -> Unit)?) = showInfoDialog(type = InfoDialog.MessageType.INFO, msg = msg)

    fun showMessageDialog(msgRes: Int) = showMessageDialog(context()?.getString(msgRes))

    fun showMessageDialog(msgRes: Int, closeCallback: (() -> Unit)?) = showMessageDialog(context()?.getString(msgRes), closeCallback)

    fun showErrorDialog(error: String?) = showInfoDialog(type = InfoDialog.MessageType.EXCEPTION, msg = error)

}