package restaurants.common.core.util

import android.os.Handler
import android.os.Looper

object ThreadUtil {

    fun runOnUiThread(runnable: () -> Unit) {
        Handler(Looper.getMainLooper()).post(runnable)
    }
}
