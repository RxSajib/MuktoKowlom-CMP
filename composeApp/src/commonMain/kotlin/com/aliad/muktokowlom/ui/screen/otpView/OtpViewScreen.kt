package com.aliad.muktokowlom.ui.screen.otpView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.AnimatedOtpInput
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Please_type_the_verification_code_send_to
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.already_have_account
import muktokowlomcmp.composeapp.generated.resources.enter_otp
import muktokowlomcmp.composeapp.generated.resources.sign_up_now
import muktokowlomcmp.composeapp.generated.resources.verification_code
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtpViewScreen(backStack: NavBackStack<NavKey>, emailOrPhoneNumber: String) {

    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.enter_otp),
                    onBackPress = {
                        backStack.remove(AppDestination.Auth.Otp(emailOrPhoneNumber = emailOrPhoneNumber))
                    },
                    editProfile = {})
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
                HeightGap(height = 10.dp)
                Text(
                    text = stringResource(Res.string.verification_code),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                HeightGap(height = 20.dp)

                Text(
                    text = stringResource(Res.string.Please_type_the_verification_code_send_to),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = emailOrPhoneNumber,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                HeightGap(height = 15.dp)

                AnimatedOtpInput { otpNumber ->

                }

                HeightGap(height = 15.dp)




            }
        }
    }
}