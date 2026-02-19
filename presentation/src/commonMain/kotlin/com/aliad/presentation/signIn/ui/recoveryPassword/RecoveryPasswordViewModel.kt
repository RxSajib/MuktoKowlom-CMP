package com.aliad.presentation.signIn.ui.recoveryPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sajib.data.appConstant.AppConstant

class RecoveryPasswordViewModel : ViewModel() {

    var inputEmailAddress by mutableStateOf("")

    val isEmailInputEmpty : Boolean get() = inputEmailAddress.isEmpty()
    var isValidEmailAddress by mutableStateOf(false)

    fun onEmailChanged(inputEmailAddressInput: String) {
        val valid = AppConstant.emailRegex.matches(inputEmailAddressInput)
        isValidEmailAddress = valid
    }

    val isButtonValid get() =  !isEmailInputEmpty && isValidEmailAddress
}