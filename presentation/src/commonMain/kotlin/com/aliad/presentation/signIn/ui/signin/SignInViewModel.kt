package com.aliad.presentation.signIn.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.User
import com.aliad.usecase.LoginUseCase
import com.aliad.usecase.dataStore.SaveStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel constructor(
    val loginUseCase: LoginUseCase,
    val saveStringData: SaveStringData
) : ViewModel() {


    val userMutableSharedFlow = MutableSharedFlow<User>()


    // aliadpolok@gmail.com
    // Aliad321@@
    fun loginAccount() {
        viewModelScope.launch {
            val response =
                loginUseCase.loginAccount(email = inputEmailAddressInput, password = passwordInput)
            if (response.isSuccess) {
                response.getOrNull()?.let {
                    saveUserInfo(user = it)
                    userMutableSharedFlow.emit(it)
                }
                //   print("login success")
            } else {
                //      print("login failed ${response.exceptionOrNull()?.message?: "Something went wrong"}")
            }
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


    fun saveUserInfo(user: User) {
        viewModelScope.launch {
            val job1 = async {
                saveStringData.saveStringData(AppConstant.USER_EMAIL_ADDRESS, user.email ?: "")
            }
            val job2 = async {
                saveStringData.saveStringData(AppConstant.USER_NAME, user.name ?: "")
            }
            val job3 = async {
                saveStringData.saveStringData(AppConstant.USER_PHONE, user.phone ?: "")
            }
            val job4 = async {
                saveStringData.saveStringData(
                    AppConstant.USER_PROFILE_IMAGE,
                    user.profileImage ?: ""
                )
            }
            val job5 = async {
                saveStringData.saveStringData(AppConstant.ACCESS_TOKEN, user.accessToken?: "")
            }
            val job6 = async {
                saveStringData.saveStringData(AppConstant.USER_REGISTER_DATE, user.createAtDate?: "")
            }
            job1.join()
            job2.join()
            job3.join()
            job4.join()
            job5.join()
            job6.join()
        }

    }
}