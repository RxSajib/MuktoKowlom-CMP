package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.ApiResponse
import com.aliad.model.User

interface AccountRepository {

    suspend fun loginAccount(email: String, password: String): ApiResult<User>
    suspend fun signUpAccount(
        name: String,
        emailAddress: String,
        password: String,
        confirmPassword: String,
        firstName: String,
        lastName: String,
        isWriterStatus: String
    ) : ApiResult<ApiResponse>
}