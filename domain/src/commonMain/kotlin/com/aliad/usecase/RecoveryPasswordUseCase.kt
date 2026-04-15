package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.ResetPasswordResponse
import com.aliad.repository.AccountRepository

class RecoveryPasswordUseCase constructor(
    val accountRepository: AccountRepository
) {

    suspend fun resetPassword(emailAddress : String) : ApiResult<ResetPasswordResponse>{
        return accountRepository.resetPassword(emailAddress = emailAddress)
    }
}