package restaurants.feature.splash

import androidx.fragment.app.FragmentActivity
import com.sha.kamel.navigator.FragmentNavigator
import org.koin.android.viewmodel.ext.android.viewModel
import restaurant.common.presentation.navigation.Fragments
import restaurant.common.presentation.navigation.fragmentFrom
import restaurant.common.presentation.ui.frag.BaseFrag

class SplashFrag : BaseFrag<SplashVm>() {

    override var layoutId: Int = R.layout.frag_splash

    override val vm: SplashVm by viewModel()

    override fun doOnViewCreated() {
        FragmentNavigator(activity as FragmentActivity)
                .addDelayed(
                        fragmentFrom(Fragments.Restaurants),
                        false,
                        2000
                )
    }

}
