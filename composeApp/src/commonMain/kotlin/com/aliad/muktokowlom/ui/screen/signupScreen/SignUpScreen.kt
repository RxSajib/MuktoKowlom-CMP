package org.example.project.ui.screen.signupScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.ui.bottomSheet.PrivacyPolicyBottomSheet
import com.aliad.muktokowlom.ui.bottomSheet.TermsAndConditionBottomSheet
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.CustomSocialButton
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.aliad.presentation.signIn.ui.signup.SignUpViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.already_have_account
import muktokowlomcmp.composeapp.generated.resources.and
import muktokowlomcmp.composeapp.generated.resources.by_continuing_you_agree_to_the
import muktokowlomcmp.composeapp.generated.resources.confirm_password
import muktokowlomcmp.composeapp.generated.resources.enter_email
import muktokowlomcmp.composeapp.generated.resources.enter_name
import muktokowlomcmp.composeapp.generated.resources.enter_password
import muktokowlomcmp.composeapp.generated.resources.facebook_icon
import muktokowlomcmp.composeapp.generated.resources.google_icon
import muktokowlomcmp.composeapp.generated.resources.or_continue_with
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import muktokowlomcmp.composeapp.generated.resources.sign_in
import muktokowlomcmp.composeapp.generated.resources.sign_up
import muktokowlomcmp.composeapp.generated.resources.sign_up_account
import muktokowlomcmp.composeapp.generated.resources.sign_up_now
import muktokowlomcmp.composeapp.generated.resources.t_and_c
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


private const val TAG = "SignUpScreen"
@Composable
fun SignUpScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: SignUpViewModel = koinViewModel()
    val privacyPolicyViewModel: PrivacyPolicyViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.isSignUpSuccess.collect { loginSuccess ->
            if (loginSuccess) {
                SnackBarEvent.save(
                    details = SnackBarDetails(
                        details = "Enter your otp for verification",
                        show = true,
                        leftIcon = Icons.Default.LockOpen
                    )
                )
                backStack.add(AppDestination.Auth.Otp(emailOrPhoneNumber = viewModel.emailInput))
            } else {
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

    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = stringResource(Res.string.sign_up),
                onBackPress = {
                    try {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }else {
                            rootBackStack.removeLastOrNull()
                        }
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                },
                editProfile = {})
        }
    ) { innerPadding ->


        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .imePadding()
            ) {


                Column(
                    modifier = Modifier .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    HeightGap(height = 10.dp)
                    Text(
                        text = stringResource(Res.string.sign_up_now),
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
                            text = stringResource(Res.string.already_have_account),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = adjustedFontSize(10.0f)
                            )
                        )
                        WidthGap(width = 4.dp)
                        Text(
                            text = stringResource(Res.string.sign_in),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.W500,
                                color = Color.Red,
                                fontSize = adjustedFontSize(10.0f)
                            ), modifier = Modifier.clickable {
                                viewModel.isOpenPrivacyPolicyBottomSheet = true
                            })
                    }
                    HeightGap(height = 20.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.enter_name),
                        text = viewModel.nameInput,
                        onValueChange = { nameInput ->
                            viewModel.nameInput = nameInput
                        },
                        isPasswordInput = false,
                        isVisiblePasswordChange = {},
                        isPasswordVisibility = true,
                    ){}
                    HeightGap(height = 10.dp)
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
                        placeHolderText = stringResource(Res.string.enter_password),
                        text = viewModel.passwordInput,
                        onValueChange = { passwordInput ->
                            viewModel.passwordInput = passwordInput
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isPasswordShows.value = !viewModel.isPasswordShows.value
                        },
                        isPasswordVisibility = viewModel.isPasswordShows.value
                    ){}
                    HeightGap(height = 10.dp)
                    MyCustomInputFiled(
                        placeHolderText = stringResource(Res.string.confirm_password),
                        text = viewModel.confirmPasswordInput,
                        onValueChange = { conformPasswordInput ->
                            viewModel.confirmPasswordInput = conformPasswordInput
                        },
                        isPasswordInput = true,
                        isVisiblePasswordChange = {
                            viewModel.isConfirmPasswordShows.value =
                                !viewModel.isConfirmPasswordShows.value
                        },
                        isPasswordVisibility = viewModel.isConfirmPasswordShows.value
                    ){}
                    HeightGap(height = 20.dp)


                    Text(
                        text = stringResource(Res.string.or_continue_with),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    HeightGap(height = 10.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomSocialButton(
                            icon = painterResource(Res.drawable.facebook_icon),
                            backGroundColor = onPrimaryLight
                        ) {

                        }
                        WidthGap(width = 10.dp)
                        CustomSocialButton(
                            icon = painterResource(Res.drawable.google_icon),
                            backGroundColor = Color.Red
                        ) {

                        }
                    }
                    HeightGap(height = 10.dp)
                }


                MyCustomButton(
                    title = stringResource(Res.string.sign_up_account),
                    modifier = Modifier,
                    onClickButton = {
                        viewModel.signUpAccount()
                    },
                    isEnable = viewModel.isSignUpButtonEnable,
                    showProgress = viewModel.isLoading
                )
                HeightGap(height = 10.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.by_continuing_you_agree_to_the),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = adjustedFontSize(9f), fontWeight = FontWeight.W400
                        ),
                    )

                    WidthGap(
                        width = 4.dp
                    )

                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val underlineColor = MaterialTheme.colorScheme.primary
                        Text(
                            text = stringResource(Res.string.t_and_c),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = adjustedFontSize(9f), fontWeight = FontWeight.W400
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier

                                .drawBehind {
                                    val verticalOffset = size.height - 2.sp.toPx()
                                    drawLine(
                                        color = underlineColor,
                                        strokeWidth = 1f,
                                        start = Offset(0f, verticalOffset),
                                        end = Offset(size.width, verticalOffset)
                                    )
                                }.clickable{
                                    viewModel.isOpenTermsAndConditionBottomSheet = true
                                }
                        )
                    }
                    WidthGap(
                        width = 4.dp
                    )

                    Text(
                        text = stringResource(Res.string.and),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = adjustedFontSize(9f),
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                    WidthGap(
                        width = 4.dp
                    )

                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val underlineColor = Color.Blue
                        Text(
                            text = stringResource(Res.string.privacy_policy),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.W400, fontSize = adjustedFontSize(9f)
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier

                                .drawBehind {
                                    val verticalOffset = size.height - 2.sp.toPx()
                                    drawLine(
                                        color = underlineColor,
                                        strokeWidth = 1f,
                                        start = Offset(0f, verticalOffset),
                                        end = Offset(size.width, verticalOffset)
                                    )
                                }.clickable{
                                    viewModel.isOpenPrivacyPolicyBottomSheet = true
                                }
                        )
                    }
                }
            }

            if (viewModel.isOpenPrivacyPolicyBottomSheet) {
                PrivacyPolicyBottomSheet(privacyPolicyViewModel = privacyPolicyViewModel) {
                    viewModel.isOpenPrivacyPolicyBottomSheet = false
                }
            }

            if(viewModel.isOpenTermsAndConditionBottomSheet){
                TermsAndConditionBottomSheet(
                    privacyPolicyViewModel = privacyPolicyViewModel,
                ) {
                    viewModel.isOpenTermsAndConditionBottomSheet = false
                }
            }
        }
    }
}

