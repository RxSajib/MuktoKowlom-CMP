package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.User
import com.aliad.repository.AccountRepository

class UpdatePasswordUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun updatePassword(userID : String, oldPassword : String,  password : String, confirmPassword : String) : ApiResult<User> = accountRepository.updatePassword(
        userID = userID,
        oldPassword = oldPassword,
        password = password,
        confirmPassword = confirmPassword
    )
}