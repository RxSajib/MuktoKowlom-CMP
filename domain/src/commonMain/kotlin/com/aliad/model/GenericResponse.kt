package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse<T : Any>(
    val access_token : String?= null,
    val success: Boolean = false,
    val message_bn: String ?= null,
    val message_en: String ?= null,
    val message: String,
    val data: T? = null,
    val errorResponse : ErrorResponse? = null
)

@Serializable
data class ErrorResponse(val code: Int = 400, val path: String = "", val message: String = "")