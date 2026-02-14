package com.aliad.usecase

import com.aliad.model.User
import com.aliad.repository.AccountRepository

class LoginUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun loginAccount(email : String, password : String) : Result<User> {
        val response = accountRepository.loginAccount(email = email, password = password)
        if(response.isSuccess){
            return Result.success(response.getOrNull()?: User())
        }else {
            return Result.failure(response.exceptionOrNull()?: Exception("Something went wrong"))
        }
    }
}