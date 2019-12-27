package leavesc.hello.core.http

import kotlinx.coroutines.*
import leavesc.hello.core.http.api.ApiService
import leavesc.hello.core.http.model.BaseResponse

/**
 * 作者：leavesC
 * 时间：2019/5/31 11:16
 * 描述：
 */
open class BaseRemoteDataSource(private val baseViewModelEvent: IBaseViewModeScope?) {

    protected fun getService(): ApiService = getService(
            ApiService::class.java,
            HttpConfig.BASE_URL_MAP
    )

    protected fun <T : Any> getService(clz: Class<T>, host: String): T {
        return RetrofitManagement.instance.getService(clz, host)
    }

    protected val scope
        get() = baseViewModelEvent?.lViewModelScope ?: GlobalScope

    protected fun <T> execute(block: suspend () -> BaseResponse<T>, callback: RequestCallback<T>?, quietly: Boolean = false): Job {
        return scope.launch(Dispatchers.IO) {
            try {
                if (!quietly) {
                    withContext(Dispatchers.Main) {
                        showLoading()
                    }
                }
                val response = block()
                callback?.let {
                    if (response.isSuccess) {
                        withContext(Dispatchers.Main) {
                            callback.onSuccess(response.data)
                        }
                    } else {
                        handleException(ServerBadException("服务器异常"), callback)
                    }
                }
            } catch (throwable: Throwable) {
                handleException(throwable, callback)
            } finally {
                if (!quietly) {
                    withContext(Dispatchers.Main) {
                        dismissLoading()
                    }
                }
            }
        }
    }

    private suspend fun <T> handleException(throwable: Throwable, callback: RequestCallback<T>?) {
        callback?.let {
            withContext(Dispatchers.Main) {
                val error = throwable.message ?: "未知错误"
                when (callback) {
                    is RequestMultiplyToastCallback -> {
                        showToast(error)
                        if (it is BaseException) {
                            callback.onFail(it)
                        } else {
                            callback.onFail(ServerBadException(error))
                        }
                    }
                    is RequestMultiplyCallback -> {
                        if (it is BaseException) {
                            callback.onFail(it)
                        } else {
                            callback.onFail(ServerBadException(error))
                        }
                    }
                    else -> {
                        showToast(error)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        baseViewModelEvent?.showLoading()
    }

    private fun dismissLoading() {
        baseViewModelEvent?.dismissLoading()
    }

    private fun showToast(msg: String) {
        baseViewModelEvent?.showToast(msg)
    }

}