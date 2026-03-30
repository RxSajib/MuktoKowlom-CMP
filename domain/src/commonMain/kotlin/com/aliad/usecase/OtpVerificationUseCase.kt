package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.User
import com.aliad.repository.AccountRepository

class OtpVerificationUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun otpVerification(otp: String): ApiResult<User> {
        return accountRepository.otpVerification(otp = otp)
    }
}