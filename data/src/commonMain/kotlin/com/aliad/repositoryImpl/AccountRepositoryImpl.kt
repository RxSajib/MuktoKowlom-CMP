package com.aliad.repositoryImpl

import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.User
import com.aliad.repository.AccountRepository
import io.ktor.client.HttpClient

class AccountRepositoryImpl constructor(val httpClient: HttpClient) : AccountRepository {
    override suspend fun loginAccount(
        email: String,
        password: String
    ): Result<User> {
        val response = RemoteDataSources(httpClient = httpClient).loginAccount(
            email = email,
            password = password
        )
        return Result.success(
            User(
                name = "sajib",
                id = 41,
                email = "sajib",
                isVerified = 1,
                phone = "01771330378",
                phoneTwo = "01724541206",
                address = "mirpur 1",
                profileImage = ""
            )
        )
    }
}