package com.aliad.presentation.signIn.ui.privacy_policy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.PrivacyPolicy
import com.aliad.usecase.PrivacyPolicyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PrivacyPolicyViewModel constructor(val privacyPolicyUseCase: PrivacyPolicyUseCase) : ViewModel() {

    private val privacyPolicyMutableStateFlow = MutableStateFlow<PrivacyPolicy?>(null)
    val privacyPolicy = privacyPolicyMutableStateFlow.asStateFlow()

    init {
        getPrivacyPolicy()
    }

    fun getPrivacyPolicy(){
        viewModelScope.launch {
            val result = privacyPolicyUseCase.getPrivacyPolicy()
            if(result.isSuccess){
                print("get privacy policy ${result.getOrNull()}")
                privacyPolicyMutableStateFlow.emit( result.getOrNull())
            }else {
                print("get privacy policy null}")
                privacyPolicyMutableStateFlow.emit(null)
            }
        }
    }
}