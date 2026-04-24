package com.aliad.presentation.signIn.ui.editProfile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {

    var takeProfileImageFromGallery by mutableStateOf(false)

    var isOpenDatePicker by mutableStateOf(false)

    private var firstNameMutableStateFlow = MutableStateFlow<String>("")
    val firstNameState = firstNameMutableStateFlow.asStateFlow()

    private var lastNameMutableStateFlow = MutableStateFlow<String>("")
    val lastNameState = lastNameMutableStateFlow.asStateFlow()

    private var emailAddressMutableStateFlow = MutableStateFlow<String>("")
    val emailAddressState = emailAddressMutableStateFlow.asStateFlow()

    private var phoneNumberMutableStateFlow = MutableStateFlow<String>("")
    val phoneNumberState = phoneNumberMutableStateFlow.asStateFlow()

    private val secondNumberMutableStateFlow = MutableStateFlow<String>("")
    val secondNumberState = secondNumberMutableStateFlow.asStateFlow()

    private val dateOfBirthMutableStateFlow = MutableStateFlow<String>("")
    val dateOfBirthState = dateOfBirthMutableStateFlow.asStateFlow()

    private var ageMutableStateFlow = MutableStateFlow<String>("")
    val ageStateFlow = ageMutableStateFlow.asStateFlow()

    private var addressMutableStateFlow = MutableStateFlow<String>("")
    val addressState = addressMutableStateFlow.asStateFlow()


    fun updateFirstName(firstName: String) {
        viewModelScope.launch {
            firstNameMutableStateFlow.emit(firstName)
        }
    }

    fun updateLastName(lastName: String) {
        viewModelScope.launch {
            lastNameMutableStateFlow.emit(lastName)
        }
    }

    fun updateEmailAddress(emailAddress: String) {
        viewModelScope.launch {
            emailAddressMutableStateFlow.emit(emailAddress)
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            phoneNumberMutableStateFlow.emit(phoneNumber)
        }
    }

    fun updateSecondPhoneNumber(secondNumber: String) {
        viewModelScope.launch {
            secondNumberMutableStateFlow.emit(secondNumber)
        }
    }

    fun updateDateOfBirth(dateOfBirth: String) {
        viewModelScope.launch {
            dateOfBirthMutableStateFlow.emit(dateOfBirth)
        }
    }

    fun updateAge(age: String) {
        viewModelScope.launch {
            ageMutableStateFlow.emit(age)
        }
    }

    fun updateAddress(address: String) {
        viewModelScope.launch {
            addressMutableStateFlow.emit(address)
        }
    }


    val isValidationSaveButton = combine(
        firstNameState,
        lastNameState,
        emailAddressState,
        phoneNumberState
    ) { firstName, lastName, emailAddress, phoneNumber ->
        firstName.isNotBlank() && lastName.isNotBlank() && emailAddress.isNotBlank() && phoneNumber.isNotBlank()
    }

}