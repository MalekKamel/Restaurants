package restaurant.common.presentation.navigation

import androidx.fragment.app.Fragment

fun <T: Fragment> fragmentFrom(addressableFragment: AddressableFragment): T {
    return Class.forName(addressableFragment.className).newInstance() as T
}

interface AddressableFragment {
    /**
     * The fragment class name.
     */
    val className: String
}

object Fragments {

    object Splash: AddressableFragment {
        override val className: String = "$FEATURE_PACKAGE_NAME.splash.SplashFrag"
    }

    object Restaurants: AddressableFragment {
        override val className: String = "$FEATURE_PACKAGE_NAME.restaurants.RestaurantsFrag"
    }
}