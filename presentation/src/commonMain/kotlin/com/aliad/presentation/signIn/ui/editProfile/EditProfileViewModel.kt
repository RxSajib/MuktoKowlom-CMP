package com.aliad.presentation.signIn.ui.editProfile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.GenericResponse
import com.aliad.model.User
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.usecase.UpdateProfileUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.aliad.usecase.dataStore.SaveStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

private const val TAG = "EditProfileViewModel"

class EditProfileViewModel constructor(
    val getStringData: GetStringData,
    val getIntData: GetIntData,
    val saveStringData: SaveStringData,
    val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    var data = MutableSharedFlow<GenericResponse<User>>()
    var isLoading by mutableStateOf(false)

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


    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }

    // get user info
    val userID = flow {
        emit(getIntData.getIntData(key = AppConstant.USER_ID).first())
    }
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


    fun updateProfile() {
        viewModelScope.launch {
            userID.collect { userID ->
                isLoading = true
                val response = updateProfileUseCase.editProfileInfo(
                    userID = userID.toString(),
                    name = firstNameMutableStateFlow.value + " " + lastNameMutableStateFlow.value,
                    emailAddress = emailAddressMutableStateFlow.value,
                    phone = phoneNumberMutableStateFlow.value,
                    phoneTwo = secondNumberMutableStateFlow.value,
                    address = addressMutableStateFlow.value,
                    bio = ""
                )
                isLoading = false

                when (response) {
                    is ApiResult.Success -> {
                        MyCustomLogger.logInfo(tag = TAG, message = "success updating profile")
                        data.emit(
                            value = GenericResponse(
                                success = true,
                                data = response.data
                            )
                        )
                        updateProfileInfo(user = response.data)
                    }

                    is ApiResult.Error -> {
                        MyCustomLogger.logInfo(tag = TAG, message = "error updating profile")
                        data.emit(
                            value = GenericResponse(
                                success = false,
                                message_bn = response.messageBn,
                                message_en = response.messageEn
                            )
                        )
                    }
                }
            }

        }
    }

    suspend fun updateProfileInfo(user: User) {
        supervisorScope {
            awaitAll(
                async {
                    saveStringData.saveStringData(AppConstant.USER_EMAIL_ADDRESS, user.email ?: "")
                },
                async {
                    saveStringData.saveStringData(AppConstant.USER_NAME, user.name ?: "")
                },
                async {
                    saveStringData.saveStringData(
                        AppConstant.USER_PROFILE_IMAGE,
                        user.profileImage ?: ""
                    )
                },

                async {
                    saveStringData.saveStringData(
                        AppConstant.USER_REGISTER_DATE,
                        user.createAtDate ?: ""
                    )
                },

                async {
                    saveStringData.saveStringData(
                        key = AppConstant.USER_SECOND_NUMBER,
                        value = user.phoneTwo?: ""
                    )
                },
                async {
                    saveStringData.saveStringData(AppConstant.USER_PHONE, user.phone ?: "")
                },
                async {
                    saveStringData.saveStringData(AppConstant.USER_DATE_OF_BIRTH, dateOfBirthMutableStateFlow.value)
                },
                async {
                    saveStringData.saveStringData(AppConstant.USER_AGE, ageMutableStateFlow.value)
                },
                async {
                    saveStringData.saveStringData(AppConstant.USER_ADDRESS, addressMutableStateFlow.value)
                }
            )
        }
    }

}