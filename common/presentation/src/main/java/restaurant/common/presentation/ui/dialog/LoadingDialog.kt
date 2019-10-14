package restaurant.common.presentation.ui.dialog

import restaurant.common.presentation.R
import restaurant.common.presentation.ui.frag.BaseDialogFrag
import restaurants.common.core.util.ThreadUtil
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.data.DataManager

class LoadingDialog(var isCancellable: Boolean = false) : BaseDialogFrag<LoadingVm>() {

     override var layoutId: Int = R.layout.frag_dialog_loading

    companion object {
        fun newInstance(isCancellable: Boolean = false): LoadingDialog {
            return LoadingDialog(isCancellable)
        }
    }

}

class LoadingVm(dataManager: DataManager) : BaseViewModel(dataManager)

object LoadingDialogHelper {
    var instances: MutableList<LoadingDialog?> = mutableListOf()

    fun add(dialog: LoadingDialog){
        ThreadUtil.runOnUiThread {
            instances.forEach {
                it?.dismiss()
            }
            instances.clear()
            instances.add(dialog)
        }
    }

    fun hide() {
        ThreadUtil.runOnUiThread {
            instances.forEach { it?.dismiss() }
        }
    }

}
