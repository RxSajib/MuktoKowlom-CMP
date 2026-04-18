package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.ApiResponse
import com.aliad.model.ResetPasswordResponse
import com.aliad.repository.AccountRepository

class DeleteAccountUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun deleteAccount() : ApiResult<ApiResponse>{
        return accountRepository.deleteAccount()
    }
}