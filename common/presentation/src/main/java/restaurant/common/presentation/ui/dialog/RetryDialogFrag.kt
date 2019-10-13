package restaurant.common.presentation.ui.dialog

import kotlinx.android.synthetic.main.frag_dialog_retry.*
import restaurant.common.presentation.R
import restaurant.common.presentation.ui.vm.DefaultViewModel
import restaurant.common.presentation.ui.frag.BaseDialogFrag

class RetryDialogFrag : BaseDialogFrag<DefaultViewModel>() {

    private var retryCallback: (() -> Unit)? = null
    private var closeCallback: (() -> Unit)? = null
    private var message: String? = null

    override var layoutId: Int = R.layout.frag_dialog_retry

    override fun doOnViewCreated() {
        if (message != null)
            tv_message.text = message
    }

    override fun setupUi() {
        btn_retry.setOnClickListener {
            if (retryCallback != null)
                retryCallback!!.invoke()
            dismiss()
        }

        btn_close.setOnClickListener {
            if (closeCallback != null)
                closeCallback!!.invoke()
            dismiss()
        }
    }

    companion object {

        // Logic here  ------------------------------

        fun newInstance(retryCallback: () -> Unit, closeCallback: () -> Unit): RetryDialogFrag {
            val fragment = RetryDialogFrag()
            fragment.retryCallback = retryCallback
            fragment.closeCallback = closeCallback
            //        fragment.e = e;
            return fragment
        }

        fun newInstance(message: String, retryCallback: () -> Unit, closeCallback: () -> Unit): RetryDialogFrag {
            val fragment = RetryDialogFrag()
            fragment.message = message
            fragment.retryCallback = retryCallback
            fragment.closeCallback = closeCallback
            //        fragment.e = e;
            return fragment
        }
    }
}


