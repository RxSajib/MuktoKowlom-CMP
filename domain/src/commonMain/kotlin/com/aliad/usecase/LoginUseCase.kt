package com.aliad.usecase

import com.aliad.repository.AccountRepository

class LoginUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun loginAccount(email : String, password : String) = accountRepository.loginAccount(email = email, password = password)
}