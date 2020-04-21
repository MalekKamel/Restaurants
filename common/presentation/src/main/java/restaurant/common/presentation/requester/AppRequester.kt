package restaurant.common.presentation.requester

import com.sha.coroutinerequester.CoroutineRequester
import com.sha.coroutinerequester.Presentable
import com.sha.coroutinerequester.RequestOptions

class AppRequester(presentable: Presentable) {
    private val requester: CoroutineRequester by lazy {
        CoroutineRequester.create(presentable)
    }

    suspend fun request(
        requestInfo: RequestOptions = RequestOptions.defaultInfo(),
        block: suspend () -> Unit
    ){
        requester.request(requestInfo, block)
    }
}
