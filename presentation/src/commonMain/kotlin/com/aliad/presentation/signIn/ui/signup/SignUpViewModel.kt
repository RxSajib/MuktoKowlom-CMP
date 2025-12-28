package com.aliad.presentation.signIn.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sajib.data.appConstant.AppConstant


class SignUpViewModel : ViewModel() {


    var nameInput by mutableStateOf("")
    var emailInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")

    var isConfirmPasswordShows = mutableStateOf(false)
    var isPasswordShows = mutableStateOf(false)

    val isValidEmailAddress get() = AppConstant.emailRegex.matches(emailInput)




    val isSignUpButtonEnable
        get() = nameInput.isNotEmpty() && emailInput.isNotEmpty() && passwordInput.isNotEmpty() && confirmPasswordInput.isNotEmpty()
                && passwordInput.length >= 6 && passwordInput == confirmPasswordInput && isValidEmailAddress



    init {
        print("sign up viewmodel is init")
    }

    override fun onCleared() {
        print("sign up viewmodel is cleared")
        super.onCleared()
    }
}