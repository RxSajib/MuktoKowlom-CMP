package com.aliad.muktokowlom.ui.screen.updatePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.updatePassword.UpdatePasswordViewModel
import com.sajib.data.appConstant.AppConstant
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.confirm_password
import muktokowlomcmp.composeapp.generated.resources.dont_have_an_account
import muktokowlomcmp.composeapp.generated.resources.enter_email
import muktokowlomcmp.composeapp.generated.resources.enter_new_password
import muktokowlomcmp.composeapp.generated.resources.enter_old_password
import muktokowlomcmp.composeapp.generated.resources.password_update
import muktokowlomcmp.composeapp.generated.resources.password_update_success
import muktokowlomcmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UpdatePasswordScreen(backStack: NavBackStack<NavKey>, data: AppDestination.Dest.UpdatePassword){

    val viewModel : UpdatePasswordViewModel = koinViewModel()
    val dataStoreViewModel : DataStoreViewModel = koinViewModel()
    val userID by dataStoreViewModel.getIntData(key = AppConstant.USER_ID).collectAsStateWithLifecycle(null)
    viewModel.emailInput = data.emailAddress
    val successData = stringResource(Res.string.password_update_success)

    val emailAddress = viewModel.emailStateFlow.collectAsStateWithLifecycle()
    val oldPassword = viewModel.oldPasswordStateFlow.collectAsStateWithLifecycle()
    val newPassword = viewModel.newPasswordState.collectAsStateWithLifecycle()
    val confirmPassword = viewModel.confirmPasswordState.collectAsStateWithLifecycle()
    val isEnableUpdateButton = viewModel.isUpdateButtonEnable.collectAsStateWithLifecycle(false)
    val selectedLocal = dataStoreViewModel.getStringData(AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")


    LaunchedEffect(Unit){
        viewModel.updateEmailInput(emailAddress = data.emailAddress)
    }

    // handle error response
    LaunchedEffect(Unit){
        viewModel.data.collect { response ->
            response.success?.let { isSuccess ->
                if(isSuccess){
                    SnackBarEvent.save(
                        details = SnackBarDetails(
                            details = successData,
                            show = true,
                            leftIcon = Icons.Default.LockOpen
                        )
                    )
                    backStack.remove(AppDestination.Dest.UpdatePassword(emailAddress = data.emailAddress))
                } else {
                    SnackBarEvent.save(
                        details = SnackBarDetails(
                            details = if(selectedLocal.value == "en") response.message_en else response.message_bn,
                            show = true,
                            leftIcon = Icons.Default.LockOpen
                        )
                    )
                }
            }

        }
    }



    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.password_update),
                    onBackPress = {
                        backStack.remove(AppDestination.Dest.UpdatePassword(emailAddress = data.emailAddress))
                    },
                    editProfile = {}
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).imePadding().padding(16.dp)) {
                Column(modifier = Modifier.weight(1f).verticalScroll(state = rememberScrollState())) {
                    HeightGap(height = 20.dp)
                    Text(
                        text = stringResource(Res.string.password_update),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W500
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    HeightGap(height = 20.dp)

                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_email),
                        text = emailAddress.value,
                        onValueChange = { emailInput ->
                         //   viewModel.emailInput = emailInput
                            viewModel.updateEmailInput(emailAddress = emailInput)
                        },
                        isPasswordInput = false,
                        isVisiblePasswordChange = {},
                        isPasswordVisibility = true,
                        readOnly = true
                    ){}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_old_password),
                        text = oldPassword.value,
                        onValueChange = { oldPasswordInput ->
                          //  viewModel.oldPasswordInput = passwordInput
                            viewModel.updateOldPassword(oldPassword = oldPasswordInput)
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isOldPasswordShow = !viewModel.isOldPasswordShow
                        },
                        isPasswordVisibility = viewModel.isOldPasswordShow
                    ){}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_new_password),
                        text = newPassword.value,
                        onValueChange = { newPassword ->
                         //   viewModel.newPasswordInput = passwordInput
                            viewModel.newPasswordUpdate(newPassword = newPassword)
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isNewPasswordShow = !viewModel.isNewPasswordShow
                        },
                        isPasswordVisibility = viewModel.isNewPasswordShow
                    ){}

                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.confirm_password),
                        text = confirmPassword.value,
                        onValueChange = { confirmPassword ->
                           // viewModel.confirmPasswordInput = passwordInput
                            viewModel.confirmPasswordUpdate(confirmPassword = confirmPassword)
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isConfirmPasswordShow = !viewModel.isConfirmPasswordShow
                        },
                        isPasswordVisibility = viewModel.isConfirmPasswordShow
                    ){}
                }
                HeightGap(height = 20.dp)
                MyCustomButton(
                    title = stringResource(Res.string.save),
                    modifier = Modifier,
                    onClickButton = {
                        viewModel.updatePassword(userID = userID.toString())
                    },
                    isEnable = isEnableUpdateButton.value,
                    showProgress = viewModel.isLoading
                )
            }
        }
    }
}