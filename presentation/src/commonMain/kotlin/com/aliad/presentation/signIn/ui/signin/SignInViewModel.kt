package com.aliad.presentation.signIn.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.usecase.LoginUseCase
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.launch

class SignInViewModel constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    init {
        loginAccount()
    }

    private fun loginAccount(){
        viewModelScope.launch {
            loginUseCase.loginAccount(email = "aliadpolokgmail.com", password = "Aliad321@@")
        }
    }

    var inputEmailAddressInput by mutableStateOf("")

    var passwordInput by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var isValidEmailAddress by mutableStateOf(false)

    fun onEmailChanged(inputEmailAddressInput: String) {
        val valid = AppConstant.emailRegex.matches(inputEmailAddressInput)
        isValidEmailAddress = valid
    }

    val isButtonEnableForSignIn: Boolean
        get() = isValidEmailAddress && passwordInput.isNotEmpty() && passwordInput.length >= 6


}