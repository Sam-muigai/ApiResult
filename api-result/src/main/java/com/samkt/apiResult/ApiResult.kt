package com.samkt.apiResult

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String?, val throwable: Throwable) : ApiResult<Nothing>()

    fun onSuccess(block: (T) -> Unit): ApiResult<T> {
        if (this is Success) {
            block.invoke(data)
        }
        return this
    }

    fun onError(block: (String?, Throwable) -> Unit): ApiResult<T> {
        if (this is Error) {
            block(message, throwable)
        }
        return this
    }
}
