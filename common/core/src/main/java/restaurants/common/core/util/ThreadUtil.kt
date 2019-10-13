package restaurants.common.core.util

import android.os.Handler
import android.os.Looper

object ThreadUtil {

    fun runOnUiThread(runnable: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.post(runnable)
    }
}
