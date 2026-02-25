package com.aliad.muktokowlom.ui.screen.recoveryPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.aliad.presentation.signIn.ui.recoveryPassword.RecoveryPasswordViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.enter_email_address
import muktokowlomcmp.composeapp.generated.resources.enter_name
import muktokowlomcmp.composeapp.generated.resources.recovery_password
import muktokowlomcmp.composeapp.generated.resources.recovery_password_details
import muktokowlomcmp.composeapp.generated.resources.send_otp
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.sign_up_now
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecoveryPasswordScreen(backStack: NavBackStack<NavKey>) {

    val viewModel: RecoveryPasswordViewModel = koinViewModel()

    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    editProfile = {},
                    onBackPress = {
                        backStack.remove(AppDestination.Auth.RecoveryPassword)
                                  },
                    title = stringResource(Res.string.recovery_password)
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .imePadding()
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),

                        ) {

                        HeightGap(height = 10.dp)
                        Text(
                            text = stringResource(Res.string.recovery_password),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W500
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = stringResource(Res.string.recovery_password_details),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = adjustedFontSize(10.0f)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        HeightGap(height = 20.dp)
                        MyCustomInputFiled(
                            placeHolderText = stringResource(Res.string.enter_email_address),
                            text = viewModel.inputEmailAddress,
                            onValueChange = { emailAddress ->
                                viewModel.inputEmailAddress = emailAddress
                                viewModel.onEmailChanged(emailAddress)
                            },
                            isPasswordInput = false,
                            isVisiblePasswordChange = {},
                            isPasswordVisibility = true,
                        ){}

                    }

                    HeightGap(height = 20.dp)
                    MyCustomButton(
                        title = stringResource(Res.string.send_otp),
                        modifier = Modifier,
                        onClickButton = {
                            backStack.add(
                                AppDestination.Auth.Otp(emailOrPhoneNumber = viewModel.inputEmailAddress)
                            )
                        },
                        isEnable = viewModel.isButtonValid,
                        showProgress = false
                    )
                }
            }
        }
    }

}