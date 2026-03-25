package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.ApiResponse
import com.aliad.repository.AccountRepository

class SignUpUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun signUpAccount(
        name: String,
        emailAddress: String,
        password: String,
        confirmPassword: String,
        firstName: String,
        lastName: String,
        isWriterStatus: String
    ) : ApiResult<ApiResponse> {
      return  accountRepository.signUpAccount(
            name = name,
            emailAddress = emailAddress,
            password = password,
            confirmPassword = confirmPassword,
            firstName = firstName,
            lastName = lastName,
            isWriterStatus = isWriterStatus
        )
    }
}