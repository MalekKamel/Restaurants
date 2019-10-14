package com.restaurants.app

import android.os.Bundle
import android.widget.FrameLayout
import com.sha.kamel.navigator.FragmentNavigator
import restaurant.common.presentation.ui.activity.BaseActivity
import restaurants.feature.home.search.RestaurantsFrag

class MainActivity : BaseActivity<MainVm>() {

    override var layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            try {
                val frame = findViewById<FrameLayout>(R.id.mainFrame)
                FragmentNavigator(this, R.id.mainFrame)
                        .add(RestaurantsFrag.newInstance(), false)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
