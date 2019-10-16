package com.restaurants.app

import android.os.Bundle
import com.sha.kamel.navigator.FragmentNavigator
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurants.feature.restaurants.RestaurantsFrag

class MainActivity : BaseActivity() {

    override var layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            FragmentNavigator(this, R.id.mainFrame)
                    .add(RestaurantsFrag.newInstance(), false)
        }
    }
}
