package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.ApiResponse
import com.aliad.model.ResetPasswordResponse
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

    suspend fun otpVerification(otp : String) : ApiResult<User>

    suspend fun resetPassword(emailAddress : String) : ApiResult<ResetPasswordResponse>
}