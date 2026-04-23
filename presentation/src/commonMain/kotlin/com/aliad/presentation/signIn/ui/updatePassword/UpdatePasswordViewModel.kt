package com.aliad.presentation.signIn.ui.updatePassword

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.GenericResponse
import com.aliad.model.User
import com.aliad.usecase.UpdatePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UpdatePasswordViewModel constructor(
    val passwordUseCase: UpdatePasswordUseCase
) : ViewModel() {

    var emailInput by mutableStateOf("")
    var oldPasswordInput by mutableStateOf("")
    var newPasswordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")

    var isOldPasswordShow by mutableStateOf(false)
    var isNewPasswordShow by mutableStateOf(false)
    var isConfirmPasswordShow by mutableStateOf(false)

    var data = MutableSharedFlow<GenericResponse<User>>()
    var isLoading by mutableStateOf(false)
    var isSuccessPasswordUpdate by mutableStateOf(false)


    val isSubmitButtonEnable by derivedStateOf {
        oldPasswordInput.isNotBlank() &&
                newPasswordInput.isNotBlank() &&
                confirmPasswordInput.isNotBlank() &&
                confirmPasswordInput == newPasswordInput
    }


    // with combain example validation handle
    private val emailInputMutableStateFlow = MutableStateFlow<String>("")
    val emailStateFlow = emailInputMutableStateFlow.asStateFlow()

    private val oldPasswordMutableStateFlow = MutableStateFlow<String>("")
    val oldPasswordStateFlow = oldPasswordMutableStateFlow.asStateFlow()

    private val newPasswordMutableStateFlow = MutableStateFlow<String>("")
    val newPasswordState = newPasswordMutableStateFlow.asStateFlow()

    private val confirmPasswordStateFlow = MutableStateFlow<String>("")
    val confirmPasswordState = confirmPasswordStateFlow.asStateFlow()

    fun updateEmailInput(emailAddress: String) {
        viewModelScope.launch {
            emailInputMutableStateFlow.emit( emailAddress)
        }

    }

    fun updateOldPassword(oldPassword: String) {
        viewModelScope.launch {
            oldPasswordMutableStateFlow.emit( oldPassword)
        }

    }

    fun newPasswordUpdate(newPassword: String) {
        viewModelScope.launch {
            newPasswordMutableStateFlow.emit(newPassword)
        }

    }

    fun confirmPasswordUpdate(confirmPassword: String) {
        viewModelScope.launch {
            confirmPasswordStateFlow.emit(confirmPassword)
        }

    }


    val isUpdateButtonEnable = combine(
        emailStateFlow,
        oldPasswordStateFlow,
        newPasswordState,
        confirmPasswordState
    ) {emailAddress,  oldPassword, newPassword, confirmPassword ->
        emailAddress.isNotBlank() &&
        oldPassword.isNotBlank() &&
        newPassword.isNotBlank() &&
        confirmPassword.isNotBlank() &&
        newPassword == confirmPassword
    }
    // with combain example validation handle


    fun updatePassword(userID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = passwordUseCase.updatePassword(
                userID = userID,
                oldPassword = oldPasswordStateFlow.value,
                password = newPasswordState.value,
                confirmPassword = confirmPasswordState.value
            )
            isLoading = false
            when (response) {
                is ApiResult.Success -> {
                    isSuccessPasswordUpdate = true
                    data.emit(
                        value = GenericResponse(
                            success = true,
                            data = response.data
                        )
                    )
                }

                is ApiResult.Error -> {
                    isSuccessPasswordUpdate = false
                    data.emit(
                        value = GenericResponse(
                            success = false,
                            message_bn = response.messageBn,
                            message_en = response.messageEn
                        )
                    )
                }
            }
        }
    }
}