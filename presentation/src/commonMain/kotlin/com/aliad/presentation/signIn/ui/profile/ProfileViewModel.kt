package com.aliad.presentation.signIn.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.ApiException
import com.aliad.model.ApiResponse
import com.aliad.model.GenericResponse
import com.aliad.usecase.DeleteAccountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel constructor(val deleteAccountUseCase: DeleteAccountUseCase) : ViewModel() {


    var logoutDialogShow by mutableStateOf(false)
    var deleteAccountDialogShow by mutableStateOf(false)

    var data = MutableSharedFlow<ApiResponse>()

    var isLoading by mutableStateOf(false)

    fun deleteAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading  =true
            val response = deleteAccountUseCase.deleteAccount()
            isLoading  =false
            when(response){
                is ApiResult.Success -> {

                    print("success ${response.data.message_en}")
                    data.emit(ApiResponse(
                        success = true,
                        message_en = response.data.message_en,
                        message_bn = response.data.message_bn
                    ))
                }
                is ApiResult.Error -> {
                    print("failed ${response.messageEn}")
                    data.emit(ApiResponse(
                        success = false,
                        message_en = response.messageEn,
                        message_bn = response.messageBn
                    ))
                }
            }
        }
    }
}

