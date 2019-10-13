package restaurant.common.presentation.ui.view

import android.content.Context
import android.widget.Toast
import restaurant.common.presentation.ui.StatusItem
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.dialog.LoadingDialog
import restaurant.common.presentation.ui.dialog.LoadingDialogHelper
import restaurant.common.presentation.ui.dialog.RetryDialogFrag
import restaurant.common.presentation.ui.dialog.info.InfoDialog
import restaurant.common.presentation.ui.dialog.info.InfoDialogHelper
import restaurant.common.presentation.ui.frag.BaseDialogFrag
import restaurant.common.presentation.ui.frag.BaseFrag
import restaurants.common.core.util.FlashbarUtil
import restaurants.common.core.util.ThreadUtil
import restaurant.common.presentation.ui.vm.BaseViewModel

/**
 * Created by Sha on 9/14/17.
 */

interface BaseView {

    fun baseViewModel(): BaseViewModel?

    fun activity(): BaseActivity<*>?

    fun context(): Context? {
        return activity()
    }

    fun showRetryDialog(retryCallback: () -> Unit, closeCallback: () -> Unit) {
        ThreadUtil.runOnUiThread {
            RetryDialogFrag.newInstance(
                    retryCallback,
                    closeCallback).show(activity()!!)
        }
    }

    fun showRetryDialog(message: String, retryCallback: () -> Unit, closeCallback: () -> Unit) {
        ThreadUtil.runOnUiThread {
            RetryDialogFrag.newInstance(
                    message,
                    retryCallback,
                    closeCallback).show(activity()!!)
        }
    }

    fun showLoadingDialog() {
        ThreadUtil.runOnUiThread {

            if (activity() == null) return@runOnUiThread

            hideLoading()

            val dialog = LoadingDialog.newInstance()

            dialog.show(activity()!!)

            LoadingDialogHelper.add(dialog)
        }
    }

    fun showWarningDialog(msgRes: Int) {
        showWarningDialog(context()?.getString(msgRes))
    }

    fun showErrorDialog(errorRes: Int) {
        showErrorDialog(context()?.getString(errorRes))
    }

    fun showMessageDialog(msg: String?) {
        showMessageDialog(msg, null)
    }

    fun showMessageDialog(msg: String?, closeCallback: (() -> Unit)?) {
        showInfoDialog(type = InfoDialog.MessageType.INFO, msg = msg)
    }

    fun showMessageDialog(msgRes: Int) {
        showMessageDialog(context()?.getString(msgRes))
    }

    fun showMessageDialog(msgRes: Int, closeCallback: (() -> Unit)?) {
        showMessageDialog(context()?.getString(msgRes), closeCallback)
    }

    fun showErrorDialog(error: String?) {
        showInfoDialog(type = InfoDialog.MessageType.EXCEPTION, msg = error)
    }

    private fun showInfoDialog(msg: String?, type: InfoDialog.MessageType) {
        ThreadUtil.runOnUiThread {

            if (msg == null) return@runOnUiThread

            hideDialogs()

            val infoDialog = InfoDialog.newInstance(
                    type,
                    msg,
                    false,
                    null
            )

            infoDialog.show(activity()!!)

            InfoDialogHelper.add(infoDialog)
        }
    }

    fun showWarningDialog(msg: String?) {
        showInfoDialog(type = InfoDialog.MessageType.WARNING, msg = msg)
    }

    fun hideLoading() {
        LoadingDialogHelper.hide()
    }

    fun hideDialogs() {
        ThreadUtil.runOnUiThread {

            LoadingDialogHelper.instances.forEach { it?.dismiss() }
            InfoDialogHelper.instances.forEach { it?.dismiss() }
        }
    }

    fun fragment(): BaseFrag<*> {
        throw UnsupportedOperationException()
    }

    fun dialogFragment(): BaseDialogFrag<*> {
        throw UnsupportedOperationException()
    }

    fun toast(resId: Int) {
        Toast.makeText(context(), resId, Toast.LENGTH_LONG).show()
    }

    fun invalidateToolbar() {

    }

    fun showErrorInFlashBar(msg: String) {
        showMessageInFlashBar(StatusItem(StatusItem.ERROR, msg))
    }

    fun showErrorInFlashBar(msgRes: Int) {
        showErrorInFlashBar(context()!!.getString(msgRes))
    }

    fun showMessageInFlashBar(status: StatusItem) {
        ThreadUtil.runOnUiThread {
            if (activity() == null) return@runOnUiThread

            FlashbarUtil.show(
                    status.statusMessage,
                    status.getStatusColor(),
                    activity()!!
            )
        }
    }

    fun showSuccessInFlashBar(msg: String) {
        showMessageInFlashBar(StatusItem(StatusItem.SUCCESS, msg))
    }

    fun showSuccessInFlashBar(msgRes: Int) {
        showSuccessInFlashBar(context()!!.getString(msgRes))
    }

}
