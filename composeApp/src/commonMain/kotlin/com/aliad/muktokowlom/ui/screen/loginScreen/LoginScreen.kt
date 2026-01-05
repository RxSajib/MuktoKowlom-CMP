package com.aliad.muktokowlom.ui.screen.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.dataSource.CacheDataStore
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.BackButton
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.presentation.signIn.ui.signin.SignInViewModel
import io.ktor.http.HttpHeaders.Destination
import io.ktor.util.logging.Logger
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.dont_have_an_account
import muktokowlomcmp.composeapp.generated.resources.enter_email
import muktokowlomcmp.composeapp.generated.resources.enter_password
import muktokowlomcmp.composeapp.generated.resources.forgot_password
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.sign_in_now
import muktokowlomcmp.composeapp.generated.resources.sign_in_now_details
import muktokowlomcmp.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(backStack: NavBackStack<NavKey>) {

    val koin = getKoin()
    val scope = rememberCoroutineScope()
    val dataStore = remember { koin.get<CacheDataStore>() }
    val data = remember { mutableStateOf("") }

    scope.launch {
        dataStore.saveAnyData("ABCDESD")
    }

    LaunchedEffect(Unit){
        data.value =  dataStore.getAnyData().toString()
    }

    val viewModel : SignInViewModel = koinViewModel()

    Scaffold { innerPadding ->
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface).padding(innerPadding).fillMaxSize().padding(16.dp).verticalScroll(state = rememberScrollState())) {
            BackButton(imageVector = Icons.AutoMirrored.Default.ArrowBack, onclick = {})

            HeightGap(height = 20.dp)
            Text(
                text =  data.value
            )
            Text(
                text = stringResource(Res.string.sign_in_now),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 10.dp)
            Text(
                text = stringResource(Res.string.sign_in_now_details),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 20.dp)

            MyCustomInputFiled(
                placeHolderText = stringResource(Res.string.enter_email),
                text = viewModel.inputEmailAddressInput,
                onValueChange = { emailAddressInput ->
                    viewModel.inputEmailAddressInput = emailAddressInput
                    viewModel.onEmailChanged(emailAddressInput)
                },
                isPasswordInput = false,
                isVisiblePasswordChange = {},
                isPasswordVisibility = true
            )
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
            )
            HeightGap(height = 5.dp)
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(Res.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable {

                    })
            }
            HeightGap(height = 20.dp)
            MyCustomButton(
                title = stringResource(Res.string.sign_in_account),
                modifier = Modifier,
                onClickButton = {
                    backStack.add(AppDestination.HomeScreen)
                },
                isEnable = viewModel.isButtonEnableForSignIn)

            HeightGap(height = 20.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.dont_have_an_account),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(Res.string.sign_up),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W500,
                        color = Color.Red,
                    ), modifier = Modifier.clickable {
                        backStack.add(AppDestination.SignUpScreen)
                    })
            }
        }

    }
}