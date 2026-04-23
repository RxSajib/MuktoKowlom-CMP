package com.aliad.presentation.signIn.ui.otpVerification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.ErrorResponse
import com.aliad.model.User
import com.aliad.usecase.OtpVerificationUseCase
import com.aliad.usecase.dataStore.SaveIntData
import com.aliad.usecase.dataStore.SaveStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class OtpVerificationViewModel constructor(
    private val otpVerificationUseCase: OtpVerificationUseCase,
    val saveStringData: SaveStringData,
    val saveIntData: SaveIntData
) : ViewModel() {

    var isLoginSuccess = MutableSharedFlow<Boolean>()
    val userMutableSharedFlow = MutableSharedFlow<User>()
    var errorResponse by mutableStateOf(ErrorResponse())
    var loading by mutableStateOf(false)

    fun otpVerification(otp: String) {
        viewModelScope.launch {
            loading = true
            val response = otpVerificationUseCase.otpVerification(otp = otp)
            loading = false
            when (response) {
                is ApiResult.Success -> {
                    isLoginSuccess.emit(true)
                    print("success login ${response.data}")
                    saveUserInfo(response.data)
                    userMutableSharedFlow.emit(response.data)
                }

                is ApiResult.Error -> {
                    isLoginSuccess.emit(false)
                    errorResponse = errorResponse.copy(
                        message_bn = response.messageBn,
                        message_en = response.messageEn
                    )
                }
            }
        }
    }


    suspend fun saveUserInfo(user: User) {
        supervisorScope {
            val job1 = async {
                saveStringData.saveStringData(AppConstant.USER_EMAIL_ADDRESS, user.email ?: "")
            }
            val job2 = async {
                saveStringData.saveStringData(AppConstant.USER_NAME, user.name ?: "")
            }
            val job3 = async {
                saveStringData.saveStringData(AppConstant.USER_PHONE, user.phone ?: "")
            }
            val job4 = async {
                saveStringData.saveStringData(
                    AppConstant.USER_PROFILE_IMAGE,
                    user.profileImage ?: ""
                )
            }
            val job5 = async {
                saveStringData.saveStringData(AppConstant.ACCESS_TOKEN, user.accessToken ?: "")
            }
            val job6 = async {
                saveStringData.saveStringData(
                    AppConstant.USER_REGISTER_DATE,
                    user.createAtDate ?: ""
                )
            }
            val job7 = async {
                saveIntData.saveIntData(
                    key = AppConstant.USER_ID,
                    value = user.id?: -1
                )
            }
            job1.join()
            job2.join()
            job3.join()
            job4.join()
            job5.join()
            job6.join()
            job7.join()
        }

    }
}