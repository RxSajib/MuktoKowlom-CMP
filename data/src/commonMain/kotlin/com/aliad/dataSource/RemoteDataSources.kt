package com.aliad.dataSource

import com.aliad.model.CategoryDto
import com.aliad.model.CategoryWiseBookDto
import com.aliad.model.DashboardDto
import com.aliad.model.GenericResponse
import com.aliad.model.PrivacyPolicyDto
import com.aliad.model.SubscriptionDto
import com.aliad.model.User
import com.aliad.model.login.LoginDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.util.collections.getValue
import kotlinx.serialization.json.Json

class RemoteDataSources constructor(val httpClient: HttpClient) {

    private val BASEURL = "https://muktokowlom.com/api/"
    private val LOGINACCOUNT = "${BASEURL}user/login"
    private val CATEGORYURL = "${BASEURL}all-category"
    private val CATEGORYWISEBOOK = "${BASEURL}get-story-by-category"
    private val DASHBOARDSTORIES = "${BASEURL}get-dashboard-stories"
    private val MOSTPOPULARSTORY = "${BASEURL}get-popular-story"
    private val ALLSTORY = "${BASEURL}get-all-story-with-allSearch"
    private val NEWRELEASESTORY = "${BASEURL}get-new-realeses-story"
    private val SUBSCRIPTION_PLANS = "${BASEURL}get-subscription-plans"
    private val PRIVACY_POLICY = "${BASEURL}privacy-policy"



    suspend fun loginAccount(email: String, password: String) : Result<GenericResponse<LoginDto>> {
        try {
            val response = httpClient.post(LOGINACCOUNT) {
                parameter("email", email)
                parameter("password", password)
            }

            if (response.status.isSuccess()) {
                return Result.success(response.body<GenericResponse<LoginDto>>())
            } else {
                val errorText = response.bodyAsText()
                println("error body is $errorText")
                return Result.failure(Exception("error response with ${errorText}"))
               // println("error response with ${errorText.toString()}")
            }
            //

        } catch (e: ClientRequestException) {
            // 4xx error
            val errorBody = e.response.bodyAsText()
            return Result.failure(Exception("error response with ${errorBody}"))
       //     println("Client Error (${e.response.status}): $errorBody")

        } catch (e: ServerResponseException) {
            val errorBody = e.response.bodyAsText()
            return Result.failure(Exception("error response with ${errorBody}"))
         //   println("Server Error (${e.response.status}): $errorBody")

        } catch (e: Exception) {
            return Result.failure(Exception("error response with ${e.message}"))
          //  println("Unknown Error: ${e.message}")
        }
    }


    // get category
    suspend fun getCategory(): Result<GenericResponse<List<CategoryDto>>> {
        try {
            val response = httpClient.get(urlString = CATEGORYURL)
            if (response.status.isSuccess()) {
                val body = response.body<GenericResponse<List<CategoryDto>>>()
                println("success fetch category body $body")
                return Result.success(body)
            } else {
                return Result.failure(Exception("error fetch category"))
            }

        } catch (e: ClientRequestException) {
            return Result.failure(e)
        } catch (e: ServerResponseException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getCategoryWiseBook(
        categoryID: Int,
        searchBy: String,
        page: Int = 0
    ): GenericResponse<CategoryWiseBookDto> {
        val myResponse = httpClient.get(urlString = CATEGORYWISEBOOK) {
            parameter("category_id", categoryID)
            parameter("page", page)
            parameter("search", searchBy)
        }

        return myResponse.body<GenericResponse<CategoryWiseBookDto>>()
    }

    suspend fun getDashBoard() : Result<DashboardDto>{
        try {
            val response = httpClient.get(urlString = DASHBOARDSTORIES)
            if(response.status.isSuccess()){
                val data = response.body<DashboardDto>()
                return Result.success(data)
            }else {
                return Result.failure(Exception("error fetch category"))
            }
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        } catch (e: ServerResponseException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getStoryType(storyType : String, page : Int, searchKey : String = "All") : GenericResponse<CategoryWiseBookDto>{
       val response = httpClient.get(urlString = MOSTPOPULARSTORY){
           parameter("page", page)
           parameter("search", searchKey)
       }
        val body = response.body<GenericResponse<CategoryWiseBookDto>>()
        return body
    }

    suspend fun getSubscriptionPlans() : Result<GenericResponse<List<SubscriptionDto>>>{
        try {
            val response = httpClient.get(urlString = SUBSCRIPTION_PLANS)
            if(response.status.isSuccess()){

                val data = response.body<GenericResponse<List<SubscriptionDto>>>()

                return Result.success(data)
            }else {
                return Result.failure(Exception("error fetch category"))
            }
        } catch (e: ClientRequestException) {
            return Result.failure(e)
        } catch (e: ServerResponseException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getPrivacyPolicy() : Result<GenericResponse<PrivacyPolicyDto>> {
        try {
            val response = httpClient.get(urlString = PRIVACY_POLICY)
            if(response.status.isSuccess()){

                val data = response.body<GenericResponse<PrivacyPolicyDto>>()
             //   print("privacy policy ${data.data}")
                return Result.success(data)
            }else {
             //   print("error fetch privacy policy")
                return Result.failure(Exception("error fetch privacy policy"))
            }
        } catch (e: ClientRequestException) {
          //  print("error fetch privacy policy ${e.message}")
            return Result.failure(e)
        } catch (e: ServerResponseException) {
         //   print("error fetch privacy policy ${e.message}")
            return Result.failure(e)
        } catch (e: Exception) {
          //  print("error fetch privacy policy ${e.message}")
            return Result.failure(e)
        }
    }
}