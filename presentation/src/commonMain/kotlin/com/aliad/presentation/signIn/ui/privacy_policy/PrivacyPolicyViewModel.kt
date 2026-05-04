package com.aliad.presentation.signIn.ui.privacy_policy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.PrivacyPolicy
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.PrivacyPolicyUseCase
import com.aliad.utils.MyCustomLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PrivacyPolicyViewModel"
class PrivacyPolicyViewModel constructor(val privacyPolicyUseCase: PrivacyPolicyUseCase) : ViewModel() {

    private val privacyPolicyMutableStateFlow = MutableStateFlow<UiState<PrivacyPolicy>>(UiState.Loading)
    val privacyPolicy = privacyPolicyMutableStateFlow.asStateFlow()

    var isLoading by mutableStateOf(false)

    init {
        getPrivacyPolicy()
    }

    fun getPrivacyPolicy(){
        isLoading = true
        viewModelScope.launch {
            privacyPolicyMutableStateFlow.value = UiState.Loading

            val result = privacyPolicyUseCase.getPrivacyPolicy()
            isLoading = false
            if(result.isSuccess){
                MyCustomLogger.logInfo(tag = TAG, message = "success ")
                privacyPolicyMutableStateFlow.emit(UiState.Success(data = result.getOrNull()?: PrivacyPolicy()))
            }else {
                MyCustomLogger.logInfo(tag = TAG, message = "error ${result.exceptionOrNull()?.message?: "Something went wrong"}")
                privacyPolicyMutableStateFlow.emit(UiState.Error(
                    messageBn = "error ${result.exceptionOrNull()?.message?: "Something went wrong"}",
                    messageEn = "error ${result.exceptionOrNull()?.message?: "Something went wrong"}"
                ))
            }
        }
    }
}