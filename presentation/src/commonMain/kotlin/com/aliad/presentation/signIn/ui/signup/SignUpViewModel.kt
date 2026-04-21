package com.aliad.presentation.signIn.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.ErrorResponse
import com.aliad.usecase.SignUpUseCase
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class SignUpViewModel constructor(val signUpUseCase: SignUpUseCase) : ViewModel() {

    var isOpenPrivacyPolicyBottomSheet by mutableStateOf(false)
    var isOpenTermsAndConditionBottomSheet by mutableStateOf(false)

    var isSignUpSuccess = MutableSharedFlow<Boolean>()
    var nameInput by mutableStateOf("")
    val namePart get() = nameInput.trim().split(" ")

    var emailInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")

    var isConfirmPasswordShows = mutableStateOf(false)
    var isPasswordShows = mutableStateOf(false)

    val isValidEmailAddress get() = AppConstant.emailRegex.matches(emailInput)

    val isSignUpButtonEnable
        get() = nameInput.isNotEmpty() && emailInput.isNotEmpty() && passwordInput.isNotEmpty() && confirmPasswordInput.isNotEmpty()
                && passwordInput.length >= 6 && passwordInput == confirmPasswordInput && isValidEmailAddress


    var isLoading by mutableStateOf(false)
    var errorResponse by mutableStateOf(ErrorResponse())
    var successResponse by mutableStateOf(ErrorResponse())

    fun signUpAccount() {
        viewModelScope.launch {

            isLoading = true
            val response = signUpUseCase.signUpAccount(
                name = nameInput,
                emailAddress = emailInput,
                password = passwordInput,
                confirmPassword = confirmPasswordInput,
                firstName = namePart.firstOrNull()?: "",
                lastName = namePart.lastOrNull()?: "",
                isWriterStatus = "1"
            )
            isLoading = false

            when(response){
                is ApiResult.Success -> {
                    isSignUpSuccess.emit(true)
                    successResponse = errorResponse.copy(
                        message_bn = "Success",
                        message_en = "Success"
                    )
                }
                is ApiResult.Error -> {
                    isSignUpSuccess.emit(false)
                    errorResponse = errorResponse.copy(
                        message_bn = response.messageBn,
                        message_en = response.messageEn
                    )
                }
            }
        }
    }

}