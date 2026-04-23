package com.aliad.dataSource

import com.aliad.model.ApiException
import com.aliad.ApiResult
import com.aliad.model.ApiResponse
import com.aliad.model.BookItem
import com.aliad.model.CategoryDto
import com.aliad.model.CategoryWiseBookDto
import com.aliad.model.DashboardDto
import com.aliad.model.ErrorResponse
import com.aliad.model.ForgotPasswordDto
import com.aliad.model.GenericResponse
import com.aliad.model.PopularSearchDto
import com.aliad.model.PrivacyPolicyDto
import com.aliad.model.SubscriptionDto
import com.aliad.model.User
import com.aliad.model.LoginDto
import com.aliad.model.SearchStoryDto
import com.aliad.model.storyDetails.StoryDetailsDto
import com.aliad.model.subscription_history.SubscriptionHistoryDto
import com.aliad.muktokowlom.BuildKonfig
import com.aliad.utils.StoryType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.headers
import io.ktor.http.isSuccess
import io.ktor.http.parameters
import io.ktor.http.parametersOf
import io.ktor.util.collections.getValue
import kotlinx.serialization.json.Json

class RemoteDataSources constructor(val httpClient: HttpClient) {

    private val BASEURL = BuildKonfig.BASE_URL
    private val LOGINACCOUNT = "${BASEURL}user/login"
    private val CATEGORYURL = "${BASEURL}all-category"
    private val CATEGORYWISEBOOK = "${BASEURL}get-story-by-category"
    private val DASHBOARDSTORIES = "${BASEURL}get-dashboard-stories"
    private val MOSTPOPULARSTORY = "${BASEURL}get-popular-story"
    private val ALLSTORY = "${BASEURL}get-all-story-with-allSearch"
    private val NEWRELEASESTORY = "${BASEURL}get-new-realeses-story"
    private val SUBSCRIPTION_PLANS = "${BASEURL}get-subscription-plans"
    private val PRIVACY_POLICY = "${BASEURL}privacy-policy"
    private val GET_PROFILE_INFO = "${BASEURL}user/get-profile-information"
    private val STORY_DETAILS = "${BASEURL}get-story-details"
    private val POPULAR_SEARCH = "${BASEURL}get-dashboard-popular-search"
    private val SUBSCRIPTION_HISTORY = "${BASEURL}user/subscription-history"
    private val SIGNUP_ACCOUNT = "${BASEURL}user/signup"
    private val EMAIL_OTP_VERIFICATION = "${BASEURL}user/email-verification"
    private val SEARCH_BOOK = "${BASEURL}get-story-by-search"
    private val FORGOT_PASSWORD = "${BASEURL}forgot-password"
    private val DELETE_ACCOUNT = "${BASEURL}user/account/delete"
    private val PASSWORD_UPDATE = "${BASEURL}user/password-update"


    suspend fun updatePassword(userID : String, oldPassword : String,  password : String, confirmPassword : String) : ApiResult<GenericResponse<LoginDto>>{
        return try {
            val response = httpClient.post(urlString = PASSWORD_UPDATE){
                parameter("user_id", userID)
                parameter("old_password", oldPassword)
                parameter("password", password)
                parameter("password_confirmation", password)
            }
            if(response.status.isSuccess()){
                ApiResult.Success(response.body<GenericResponse<LoginDto>>())
            }else {
                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }
        }catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }

    suspend fun deleteAccount() : ApiResult<ApiResponse>{
        return try {
            val response = httpClient.get(urlString = DELETE_ACCOUNT)
            if(response.status.isSuccess()){
                ApiResult.Success(response.body<ApiResponse>())
            }else {
                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }
        }catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }


    suspend fun forGotPassword(emailAddress: String): ApiResult<ForgotPasswordDto> {

        return try {

            val response = httpClient.post(urlString = FORGOT_PASSWORD) {
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("email", emailAddress)
                        }
                    )
                )
            }
            if (response.status.isSuccess()) {
                ApiResult.Success(response.body<ForgotPasswordDto>())
            } else {
                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }
        } catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }

    suspend fun emailOTPVerification(otp: String): ApiResult<GenericResponse<LoginDto>> {
        return try {
            val response = httpClient.post(EMAIL_OTP_VERIFICATION) {
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("verification_code", otp)
                        }
                    )
                )
            }

            if (response.status.isSuccess()) {
                ApiResult.Success(
                    response.body()
                )
            } else {
                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }
        } catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }

    }

    suspend fun signupAccount(
        name: String,
        emailAddress: String,
        password: String,
        confirmPassword: String,
        firstName: String,
        lastName: String,
        isWritterStatus: String
    ): ApiResult<ApiResponse> {
        return try {
            val response = httpClient.post(SIGNUP_ACCOUNT) {
                parameter("name", name)
                parameter("email", emailAddress)
                parameter("password", password)
                parameter("password_confirmation", confirmPassword)
                parameter("first_name", firstName)
                parameter("last_name", lastName)
                parameter("is_writer_status", isWritterStatus)
            }
            print("sign up success ${response.status.isSuccess()}")
            if (response.status.isSuccess()) {

                ApiResult.Success(
                    response.body()
                )


            } else {

                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }


        } catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }

    suspend fun loginAccount(
        email: String,
        password: String
    ): ApiResult<GenericResponse<LoginDto>> {

        return try {

            val response = httpClient.post(LOGINACCOUNT) {
                parameter("email", email)
                parameter("password", password)
            }

            if (response.status.isSuccess()) {

                ApiResult.Success(
                    response.body()
                )

            } else {

                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }

        } catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }


    // get category
    suspend fun getCategory(): Result<GenericResponse<List<CategoryDto>>> {
        try {
            val response = httpClient.get(urlString = CATEGORYURL)
            if (response.status.isSuccess()) {
                val body = response.body<GenericResponse<List<CategoryDto>>>()
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

    suspend fun getDashBoard(): Result<DashboardDto> {
        try {
            val response = httpClient.get(urlString = DASHBOARDSTORIES)
            if (response.status.isSuccess()) {
                val data = response.body<DashboardDto>()
                return Result.success(data)
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

    suspend fun getStoryType(
        storyType: String,
        page: Int,
        searchKey: String = "All"
    ): GenericResponse<CategoryWiseBookDto> {


        val URL =
            if (storyType == StoryType.MOST_POPULAR_STORY.name) MOSTPOPULARSTORY else if (storyType == StoryType.ALL_STORY.name) ALLSTORY else NEWRELEASESTORY

        val response = httpClient.get(urlString = URL) {
            parameter("page", page)
            parameter("search", searchKey)
        }
        val body = response.body<GenericResponse<CategoryWiseBookDto>>()
        return body
    }

    suspend fun getSubscriptionPlans(): Result<GenericResponse<List<SubscriptionDto>>> {
        try {
            val response = httpClient.get(urlString = SUBSCRIPTION_PLANS)
            if (response.status.isSuccess()) {

                val data = response.body<GenericResponse<List<SubscriptionDto>>>()

                return Result.success(data)
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

    suspend fun getPrivacyPolicy(): Result<GenericResponse<PrivacyPolicyDto>> {
        try {
            val response = httpClient.get(urlString = PRIVACY_POLICY)
            if (response.status.isSuccess()) {

                val data = response.body<GenericResponse<PrivacyPolicyDto>>()
                //   print("privacy policy ${data.data}")
                return Result.success(data)
            } else {
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


    suspend fun getStoryDetails(storyID: String): ApiResult<GenericResponse<SearchStoryDto>> {
        return try {
            val response = httpClient.get(urlString = STORY_DETAILS) {
                this.parameter("story_id", storyID)
            }
            if (response.status.isSuccess()) {
                ApiResult.Success(
                    response.body<GenericResponse<SearchStoryDto>>()
                )

            } else {
                val errorBody = response.bodyAsText()
                val errorResponse = try {
                    Json.decodeFromString<ErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ErrorResponse(
                        message_en = e.message ?: "Something went wrong",
                        message_bn = e.message ?: "Something went wrong",
                        success = false
                    )
                }

                ApiResult.Error(

                    messageBn = errorResponse.message_bn,
                    messageEn = errorResponse.message_en

                )
            }
        } catch (e: ClientRequestException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: ServerResponseException) {

            val errorBody = e.response.bodyAsText()

            ApiResult.Error(
                messageBn = errorBody,
                messageEn = errorBody
            )

        } catch (e: Exception) {

            ApiResult.Error(
                messageBn = e.message,
                messageEn = e.message
            )
        }
    }

    suspend fun getPopularSearch(): Result<PopularSearchDto> {
        try {
            val response = httpClient.get(urlString = POPULAR_SEARCH)
            if (response.status.isSuccess()) {
                val data = response.body<PopularSearchDto>()
                return Result.success(data)
            } else {
                return Result.failure(Exception("error fetch popular search story"))
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

    suspend fun getSubscriptionHistory(page: Int): GenericResponse<SubscriptionHistoryDto> {
        val response = httpClient.get(urlString = SUBSCRIPTION_HISTORY) {
            parameter("page", page)
        }
        //  print("response with data ${response.status.description}")
        return response.body<GenericResponse<SubscriptionHistoryDto>>()
    }

    suspend fun getStoryBySearch(searchKey: String, page: Int): GenericResponse<SearchStoryDto> {
        val response = httpClient.get(urlString = SEARCH_BOOK) {
            parameter("page", page)
            parameter("search", searchKey)
        }
        return response.body<GenericResponse<SearchStoryDto>>()
    }

    suspend fun getAllReleaseStory(searchKey: String, page: Int): GenericResponse<List<BookItem>> {
        val response = httpClient.get(urlString = ALLSTORY) {
            parameter("page", page)
            parameter("search", searchKey)
        }
        return response.body<GenericResponse<List<BookItem>>>()
    }
}