package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.User

interface AccountRepository {

    suspend fun loginAccount(email : String, password : String) : ApiResult<User>

}