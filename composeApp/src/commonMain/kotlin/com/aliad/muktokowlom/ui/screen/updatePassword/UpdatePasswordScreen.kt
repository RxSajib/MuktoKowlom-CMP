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
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.updatePassword.UpdatePasswordViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.confirm_password
import muktokowlomcmp.composeapp.generated.resources.dont_have_an_account
import muktokowlomcmp.composeapp.generated.resources.enter_email
import muktokowlomcmp.composeapp.generated.resources.enter_new_password
import muktokowlomcmp.composeapp.generated.resources.enter_old_password
import muktokowlomcmp.composeapp.generated.resources.enter_password
import muktokowlomcmp.composeapp.generated.resources.password_update
import muktokowlomcmp.composeapp.generated.resources.save
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.sign_in_now
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UpdatePasswordScreen(backStack: NavBackStack<NavKey>){

    val viewModel : UpdatePasswordViewModel = koinViewModel()

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.password_update),
                    onBackPress = {
                        backStack.remove(AppDestination.Dest.UpdatePassword)
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(Res.string.dont_have_an_account),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = adjustedFontSize(10.0f)
                            )
                        )
                    }
                    HeightGap(height = 20.dp)

                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_email),
                        text = viewModel.emailInput,
                        onValueChange = { emailInput ->
                            viewModel.emailInput = emailInput
                        },
                        isPasswordInput = false,
                        isVisiblePasswordChange = {},
                        isPasswordVisibility = true
                    ){}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_old_password),
                        text = viewModel.oldPasswordInput,
                        onValueChange = { passwordInput ->
                            viewModel.oldPasswordInput = passwordInput
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
                        text = viewModel.newPasswordInput,
                        onValueChange = { passwordInput ->
                            viewModel.newPasswordInput = passwordInput
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
                        text = viewModel.confirmPasswordInput,
                        onValueChange = { passwordInput ->
                            viewModel.confirmPasswordInput = passwordInput
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

                    },
                    isEnable = true,
                    showProgress = false
                )
            }
        }
    }
}