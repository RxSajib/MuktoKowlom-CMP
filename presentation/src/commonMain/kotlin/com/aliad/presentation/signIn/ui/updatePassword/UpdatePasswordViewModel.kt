package com.aliad.presentation.signIn.ui.updatePassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UpdatePasswordViewModel : ViewModel() {

    var emailInput by mutableStateOf("")
    var oldPasswordInput by mutableStateOf("")
    var newPasswordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")

    var isOldPasswordShow by mutableStateOf(false)
    var isNewPasswordShow by mutableStateOf(false)
    var isConfirmPasswordShow by mutableStateOf(false)
}