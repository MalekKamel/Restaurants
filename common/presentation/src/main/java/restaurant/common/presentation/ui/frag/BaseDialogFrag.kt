package restaurant.common.presentation.ui.frag

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import com.sha.kamel.navigator.FragmentNavigator
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurant.common.presentation.ui.view.ViewInterface
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.core.util.CrashlyticsUtil

/**
 * Created by Sha on 9/24/17.
 */

abstract class BaseDialogFrag<VM : BaseViewModel> : RxDialogFragment(), ViewInterface {
    var vm: VM? = null
    var isShown: Boolean = false
        protected set

    protected var isDismissed: Boolean = false

    private lateinit var activity: BaseActivity

    protected open var isCanceledOnTouchOutside: Boolean = false
        get() = false

    private var onDismissListener: (() -> Unit)? = null

    abstract var layoutId: Int
    protected open fun setupUi() {}
    protected open fun doOnViewCreated() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            activity = getActivity() as BaseActivity

        } catch (e: Exception) {
            CrashlyticsUtil.logAndPrint(e)
        }

        isShown = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(isCanceledOnTouchOutside)

        try {
            setupUi()
            doOnViewCreated()
        } catch (e: Exception) {
            CrashlyticsUtil.logAndPrint(e)
        }

    }

    @Nullable
    override fun onCreateView(
            inflater: LayoutInflater,
            @Nullable container: ViewGroup?,
            @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    protected fun transparentWindow(): Boolean {
        return true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val w = dialog.window
        // Hide title
        if (w != null) {
            w.requestFeature(Window.FEATURE_NO_TITLE)
            if (transparentWindow())
                w.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return dialog
    }

    override fun activity(): BaseActivity {
        return activity
    }

    open fun show(activity: FragmentActivity): BaseDialogFrag<VM> {
        try {
            if (!isShown)
                FragmentNavigator(activity).showDialogFragment(this)
        } catch (e: Exception) {
            CrashlyticsUtil.logAndPrint(e)
        }

        return this
    }

    fun onDismissListener(callback: () -> Unit): BaseDialogFrag<VM> {
        this.onDismissListener = callback
        return this
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (onDismissListener != null)
            onDismissListener!!.invoke()
    }

    override fun dismiss() {
        super.dismiss()
        isShown = false
        isDismissed = true
    }

    override fun getContext(): Context? {
        return activity
    }



}
