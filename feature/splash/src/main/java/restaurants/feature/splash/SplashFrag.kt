package restaurants.feature.splash

import android.os.Handler
import org.koin.android.viewmodel.ext.android.viewModel
import restaurant.common.presentation.ui.frag.BaseFrag

class SplashFrag : BaseFrag<SplashVm>() {

    override var layoutId: Int = R.layout.frag_splash

    override val vm: SplashVm by viewModel()

    override fun doOnViewCreated() {
        Handler().postDelayed(
                {
                },
                2000
        )
    }

}
