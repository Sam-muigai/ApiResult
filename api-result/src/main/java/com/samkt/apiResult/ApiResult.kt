package com.samkt.apiResult

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()

    fun onSuccess(block: (T) -> Unit): ApiResult<T> {
        if (this is Success) {
            block.invoke(data)
        }
        return this
    }

    fun onError(block: (Exception) -> Unit): ApiResult<T> {
        if (this is Error) {
            block(exception)
        }
        return this
    }
}
