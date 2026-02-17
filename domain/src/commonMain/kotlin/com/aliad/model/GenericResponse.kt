package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse<T : Any>(
    val access_token : String?= null,
    val success: Boolean? = null,
    val message_bn: String ?= null,
    val message_en: String ?= null,
    val message: String ?= null,
    val status : Boolean ?= null,
    val data: T? = null,
    val errorResponse : ErrorResponse? = null,
)

@Serializable
data class ErrorResponse(val success : Boolean  =false, val message_en : String?= null, val message_bn : String?= null)


class ApiException(
    val error: ErrorResponse,
    message: String? = error.message_en
) : Exception(message)
