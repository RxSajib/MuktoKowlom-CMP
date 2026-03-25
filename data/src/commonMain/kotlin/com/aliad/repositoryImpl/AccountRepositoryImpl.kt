package com.aliad.repositoryImpl

import com.aliad.ApiResult
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.ApiResponse
import com.aliad.model.User
import com.aliad.model.login.LoginDto
import com.aliad.model.mapper.DataMapper.toUser
import com.aliad.repository.AccountRepository
import io.ktor.client.HttpClient

/*
class AccountRepositoryImpl constructor(val httpClient: HttpClient) : AccountRepository {
    override suspend fun loginAccount(
        email: String,
        password: String
    ): Result<User> {
        val response = RemoteDataSources(httpClient = httpClient).loginAccount(
            email = email,
            password = password
        )
        if(response.isSuccess){
            val userDto = response.getOrNull()
            return Result.success(toUser(loginDto = userDto?.data?: LoginDto(), accessToken = response.getOrNull()?.access_token?: ""))
        }else {
            return Result.failure(response.exceptionOrNull()?: Exception("Something went wrong"))
        }

    }
}*/


class AccountRepositoryImpl(
    private val remoteDataSources: RemoteDataSources
) : AccountRepository {

    override suspend fun loginAccount(
        email: String,
        password: String
    ): ApiResult<User> {

        return when (val response =
            remoteDataSources.loginAccount(email, password)) {

            is ApiResult.Success -> {

                val body = response.data

                val user = toUser(
                    loginDto = body.data ?: LoginDto(),
                    accessToken = body.access_token ?: ""
                )

                ApiResult.Success(user)
            }

            is ApiResult.Error -> {

                // Directly pass error from remote layer
                ApiResult.Error(messageEn = response.messageEn, messageBn = response.messageBn)
            }
        }
    }

    override suspend fun signUpAccount(
        name: String,
        emailAddress: String,
        password: String,
        confirmPassword: String,
        firstName: String,
        lastName: String,
        isWriterStatus: String
    ): ApiResult<ApiResponse> {
        return when (
            val myResponse = remoteDataSources.signupAccount(
                name = name,
                emailAddress = emailAddress,
                password = password,
                confirmPassword = confirmPassword,
                firstName = firstName,
                lastName = lastName,
                isWritterStatus = isWriterStatus
            )


        ){
            is ApiResult.Success -> {
                val body = myResponse.data
                ApiResult.Success(body)
            }
            is ApiResult.Error -> {
                ApiResult.Error(messageEn = myResponse.messageEn, messageBn = myResponse.messageBn)
            }
        }
    }
}


