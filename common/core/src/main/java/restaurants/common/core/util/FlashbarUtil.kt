package restaurants.common.core.util

import android.app.Activity

import androidx.annotation.ColorRes

import com.andrognito.flashbar.Flashbar

object FlashbarUtil {

    fun show(
            message: String,
            @ColorRes color: Int,
            activity: Activity
    ) {
        try {
            Flashbar.Builder(activity)
                    .message(message)
                    .gravity(Flashbar.Gravity.TOP)
                    .duration(6000)
                    .backgroundColorRes(color)
                    .dismissOnTapOutside()
                    .enableSwipeToDismiss()
                    .build()
                    .show()
        } catch (e: Exception) {
            e.printStackTrace()
            CrashlyticsUtil.log(e)
        }

    }
}
