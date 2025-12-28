package com.aliad.repository

import com.aliad.model.User

interface AccountRepository {

    suspend fun loginAccount(email : String, password : String) : Result<User>

}