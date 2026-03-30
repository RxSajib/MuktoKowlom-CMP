package com.aliad.muktokowlom.ui.screen.otpView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.AnimatedOtpInput
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.otpVerification.OtpVerificationViewModel
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import muktokowlomcmp.composeapp.generated.resources.Please_type_the_verification_code_send_to
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.enter_otp
import muktokowlomcmp.composeapp.generated.resources.verification_code
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OtpViewScreen(
    backStack: NavBackStack<NavKey>,
    emailOrPhoneNumber: String,
    rootBackStack: NavBackStack<NavKey>
) {

    val viewModel : OtpVerificationViewModel = koinViewModel()
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(lifecycle.lifecycle) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.userMutableSharedFlow.collect { user ->
                rootBackStack.removeLastOrNull()
            }
        }
    }

    LaunchedEffect(Unit){
        viewModel.isLoginSuccess.collect { isLoginSuccess ->
            if(isLoginSuccess){
                SnackBarEvent.save(
                    details = SnackBarDetails(
                        details = "Login success",
                        show = true,
                        leftIcon = Icons.Default.LockOpen
                    )
                )
            }else {
                SnackBarEvent.save(
                    details = SnackBarDetails(
                        details = viewModel.errorResponse.message_bn,
                        show = true,
                        leftIcon = Icons.Default.LockOpen
                    )
                )
            }
        }
    }

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
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
                   viewModel.otpVerification(otp = otpNumber)
                }

                HeightGap(height = 15.dp)

                if(viewModel.loading){
                    WatchRunningAnimation(
                        clockColor = Color.Gray.copy(alpha = 0.1f),
                        handColor = Color.Gray,
                        clockSize = 30.dp
                    )
                }
                HeightGap(height = 15.dp)


            }
        }
    }
}