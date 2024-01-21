package com.samkt.apiResult

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> handleApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T,
): ApiResult<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            ApiResult.Success(result)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    ApiResult.Error(
                        message = "Please check your internet connection",
                        throwable = throwable,
                    )
                }

                else -> {
                    ApiResult.Error(
                        message = throwable.localizedMessage,
                        throwable = throwable,
                    )
                }
            }
        }
    }
}
