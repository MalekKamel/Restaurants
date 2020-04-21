package restaurant.common.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import restaurant.common.presentation.ui.view.ViewInterface
import restaurants.common.core.util.reportAndPrint

abstract class BaseActivity
    : AppCompatActivity(), ViewInterface {

    abstract var layoutId: Int

    protected open fun bindUi() {}
    protected open fun doOnCreate() {}
    protected open fun doOnCreate(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            if (layoutId != 0)
                setContentView(layoutId)

            bindUi()
            doOnCreate()
            doOnCreate(savedInstanceState)

        } catch (e: Exception) {
            e.reportAndPrint()
        }
    }

    override fun activity(): BaseActivity = this
}
