package com.aliad.presentation.signIn.ui.editProfile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class EditProfileViewModel constructor(
    val getStringData: GetStringData
) : ViewModel() {


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


    // get user info
    val emailAddress = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_EMAIL_ADDRESS).first())
    }

    val userProfileImage = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_PROFILE_IMAGE).first())
    }
    val userName = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_NAME).first())
    }

    val phoneNumber = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_PHONE).first())
    }
    val userSecondaryNumber = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_SECOND_NUMBER).first())
    }
    val userDateOfBirth = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_DATE_OF_BIRTH).first())
    }
    val userAge = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_AGE).first())
    }
    val userAddress = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_ADDRESS).first())
    }
    // get user info

    init {
        getUserInfo()
    }


    private fun getUserInfo() {
        viewModelScope.launch {
            supervisorScope {
                awaitAll(
                    async {
                        emailAddress.collect { emailAddress ->
                            emailAddressMutableStateFlow.emit(emailAddress)
                        }

                    },
                    async {
                        phoneNumber.collect { phoneNumber ->
                            phoneNumberMutableStateFlow.emit(phoneNumber)
                        }
                    },
                    async {
                        userSecondaryNumber.collect { userSecondaryNumber ->
                            secondNumberMutableStateFlow.emit(userSecondaryNumber)
                        }
                    },
                    async {
                        userDateOfBirth.collect { userDateOfBirth ->
                            dateOfBirthMutableStateFlow.emit(userDateOfBirth)
                        }
                    },
                    async {
                        userAge.collect { userAge ->
                            ageMutableStateFlow.emit(userAge)
                        }
                    },
                    async {
                        userAddress.collect { userAddress ->
                            addressMutableStateFlow.emit(userAddress)
                        }
                    },
                    async {
                        userName.collect { userFullName ->
                            val parts = userFullName.trim().split(" ")
                            val firstName = parts.firstOrNull() ?: ""
                            val lastName = parts.lastOrNull() ?: ""

                            firstNameMutableStateFlow.emit(firstName)
                            lastNameMutableStateFlow.emit(lastName)
                        }
                    }

                )
            }
        }
    }


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