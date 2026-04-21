package com.aliad.presentation.signIn.ui.updatePassword

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


    fun updatePassword(userID : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = passwordUseCase.updatePassword(userID = userID, password = password)
            isLoading = false
            when(response){
                is ApiResult.Success -> {
                    data.emit(value = GenericResponse(
                        success = true,
                        data = response.data
                    ))
                }
                is ApiResult.Error -> {
                    data.emit(value = GenericResponse(
                        success = false,
                        message_bn = response.messageBn,
                        message_en = response.messageEn
                    ))
                }
            }
        }
    }
}