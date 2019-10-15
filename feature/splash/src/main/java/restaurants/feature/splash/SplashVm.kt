package restaurants.feature.splash

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurant.common.presentation.ui.vm.BaseViewModel
import restaurants.common.data.DataManager

/**
 * Created by Sha on 9/15/17.
 */

val splashModule = module {
    viewModel { SplashVm(get()) }
}

class SplashVm(dataManager: DataManager) : BaseViewModel(dataManager)