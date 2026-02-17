package com.aliad

import com.aliad.model.ErrorResponse


sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(
        val messageEn: String? = null,
        val messageBn: String? = null
    ) : ApiResult<Nothing>()
}
