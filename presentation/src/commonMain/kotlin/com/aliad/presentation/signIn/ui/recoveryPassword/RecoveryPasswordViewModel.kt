package com.aliad.presentation.signIn.ui.recoveryPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.ErrorResponse
import com.aliad.usecase.RecoveryPasswordUseCase
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RecoveryPasswordViewModel constructor(
    val resetPasswordUseCase: RecoveryPasswordUseCase
) : ViewModel() {

    var inputEmailAddress by mutableStateOf("")

    val isEmailInputEmpty : Boolean get() = inputEmailAddress.isEmpty()
    var isValidEmailAddress by mutableStateOf(false)

    fun onEmailChanged(inputEmailAddressInput: String) {
        val valid = AppConstant.emailRegex.matches(inputEmailAddressInput)
        isValidEmailAddress = valid
    }

    val isButtonValid get() =  !isEmailInputEmpty && isValidEmailAddress
    var isLoading by mutableStateOf(false)

    var responseMutableSharedFlow = MutableSharedFlow<ErrorResponse>()
    val response = responseMutableSharedFlow.asSharedFlow()

    fun resetPassword() {
        isLoading = true
        viewModelScope.launch {
            val response = resetPasswordUseCase.resetPassword(emailAddress = inputEmailAddress)
            when(response){
                is ApiResult.Success -> {
                    isLoading = false

                    responseMutableSharedFlow.emit(ErrorResponse(success = true, message_bn = response.data.messageBn, message_en = response.data.messageEn))
                }

                is ApiResult.Error -> {
                    isLoading = false
                    responseMutableSharedFlow.emit(ErrorResponse(success = false, message_bn = response.messageBn, message_en = response.messageEn))

                }
            }
        }
    }
}