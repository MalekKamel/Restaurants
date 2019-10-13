package restaurant.common.presentation.exception

import com.google.gson.GsonBuilder

/**
 * Created by Sha on 10/9/17.
 */

object RetrofitUtil {
    fun parseHttpExceptionModel(body: String): HttpExceptionContract {
        return GsonBuilder().create().fromJson(body, HttpExceptionContract::class.java)
    }


}
