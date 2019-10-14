package com.restaurants.app

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import restaurants.common.data.DataManager
import restaurant.common.presentation.ui.vm.BaseViewModel

val navHostModule = module {
    viewModel { MainVm(get()) }
}

class MainVm(dataManager: DataManager) : BaseViewModel(dataManager) {

}