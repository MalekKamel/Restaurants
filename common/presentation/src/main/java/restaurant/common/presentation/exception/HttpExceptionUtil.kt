package restaurant.common.presentation.exception

import retrofit2.HttpException
import java.io.IOException

object HttpExceptionUtil {

    @Throws(IOException::class)
    fun error(exception: HttpException): String {
        return exception.response()?.errorBody()!!.string()
    }

    fun code(exception: HttpException): Int? {
        return exception.response()?.code()
    }

}