package com.aliad.muktokowlom.ui.screen.resetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.resetPassword.ResetPasswordViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.confirm_password
import muktokowlomcmp.composeapp.generated.resources.dont_have_an_account
import muktokowlomcmp.composeapp.generated.resources.enter_email
import muktokowlomcmp.composeapp.generated.resources.enter_otp
import muktokowlomcmp.composeapp.generated.resources.enter_password
import muktokowlomcmp.composeapp.generated.resources.i_don_t_found_otp
import muktokowlomcmp.composeapp.generated.resources.recovery_password
import muktokowlomcmp.composeapp.generated.resources.reset
import muktokowlomcmp.composeapp.generated.resources.reset_password
import muktokowlomcmp.composeapp.generated.resources.reset_password_details
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.sign_in_now
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResetPasswordScreen(){

    val viewModel : ResetPasswordViewModel = koinViewModel()

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(topBar = {
            MyCustomAppBar(title = stringResource(Res.string.reset_password), onBackPress = {}, editProfile = {})
        }) {innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp).imePadding()) {


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),

                    ) {

                    Text(
                        text = stringResource(Res.string.recovery_password),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W500
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.reset_password_details),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = adjustedFontSize(10.0f)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    HeightGap(height = 20.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_otp),
                        text = viewModel.otpInput,
                        onValueChange = { emailAddressInput ->
                            viewModel.otpInput = emailAddressInput
                        },
                        isPasswordInput = false,
                        isVisiblePasswordChange = {},
                        isPasswordVisibility = true
                    ) {}
                    HeightGap(height = 5.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = stringResource(Res.string.i_don_t_found_otp),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = adjustedFontSize(10.0f),
                                color = Color.Red
                            ),
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                    HeightGap(height = 20.dp)
                    MyCustomInputFiled(
                        readOnly = true,
                        placeHolderText = "",
                        text = viewModel.otpInput,
                        onValueChange = { emailAddressInput ->
                            viewModel.otpInput = emailAddressInput
                        },
                        isPasswordInput = false,
                        isVisiblePasswordChange = {},
                        isPasswordVisibility = true
                    ) {}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_password),
                        text = viewModel.passwordInput,
                        onValueChange = { passwordInput ->
                            viewModel.passwordInput = passwordInput
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                        },
                        isPasswordVisibility = viewModel.isPasswordVisible
                    ) {}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.confirm_password),
                        text = viewModel.confirmPasswordInput,
                        onValueChange = { passwordInput ->
                            viewModel.confirmPasswordInput = passwordInput
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isConfirmPasswordVisible = !viewModel.isConfirmPasswordVisible
                        },
                        isPasswordVisibility = viewModel.isConfirmPasswordVisible
                    ) {}

                }
                HeightGap(height = 10.dp)
                MyCustomButton(
                    title = stringResource(Res.string.reset),
                    modifier = Modifier,
                    onClickButton = {

                    },
                    isEnable = viewModel.isButtonEnableForResetPassword,
                    showProgress = viewModel.showProgress
                )

                HeightGap(height = 10.dp)
            }
        }
    }
}

@Composable
@Preview
fun ResetPasswordScreenPreview(){
    ResetPasswordScreen()
}