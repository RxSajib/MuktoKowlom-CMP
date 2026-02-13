package com.aliad.presentation.signIn.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {


    var logoutDialogShow by mutableStateOf(false)
    var deleteAccountDialogShow by mutableStateOf(false)
}