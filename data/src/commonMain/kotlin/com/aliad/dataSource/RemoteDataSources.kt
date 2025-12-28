package com.aliad.dataSource

import com.aliad.model.GenericResponse
import com.aliad.model.User
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.util.collections.getValue
import kotlinx.serialization.json.Json

class RemoteDataSources constructor(val httpClient: HttpClient) {

    private val BASEURL = "https://muktokowlom.com/api/"
    private val LOGINACCOUNT = "${BASEURL}user/login"

    suspend fun loginAccount(email: String, password: String) {
        try {
            val response = httpClient.post(LOGINACCOUNT) {
                parameter("email", email)
                parameter("password", password)
            }

            if(response.status.isSuccess()){
                println("login success")
            }else {
               val errorText = response.bodyAsText()
              //  val errorResponse = Json.decodeFromString<GenericResponse<Boolean>>(errorText)
                println("error response with ${errorText.toString()}")
            }
          //

        } catch (e: ClientRequestException) {
            // 4xx error
            val errorBody = e.response.bodyAsText()
            println("Client Error (${e.response.status}): $errorBody")

        } catch (e: ServerResponseException) {
            val errorBody = e.response.bodyAsText()
            println("Server Error (${e.response.status}): $errorBody")

        } catch (e: Exception) {
            println("Unknown Error: ${e.message}")
        }
    }

}