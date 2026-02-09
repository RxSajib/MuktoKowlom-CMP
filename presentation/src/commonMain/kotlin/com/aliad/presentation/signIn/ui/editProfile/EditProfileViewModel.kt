package com.aliad.presentation.signIn.ui.editProfile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {

    var firstNameInput by mutableStateOf("")
    var lastNameInput by mutableStateOf("")
    var emailAddressInput by mutableStateOf("")
    var phoneNumberInput by mutableStateOf("")
    var secondNumberInput by mutableStateOf("")
    var dateOfBirthInput by mutableStateOf("")
    var ageInput by mutableStateOf("")
    var addressInput by mutableStateOf("")
}