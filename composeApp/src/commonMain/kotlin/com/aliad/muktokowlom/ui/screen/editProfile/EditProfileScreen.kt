package com.aliad.muktokowlom.ui.screen.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomButton
import com.aliad.muktokowlom.ui.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.component.WheelDatePickerDialog
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.utils.MyCustomLogger
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.editProfile.EditProfileViewModel
import com.sajib.data.appConstant.AppConstant
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.address
import muktokowlomcmp.composeapp.generated.resources.age
import muktokowlomcmp.composeapp.generated.resources.date_of_birth
import muktokowlomcmp.composeapp.generated.resources.edit_profile
import muktokowlomcmp.composeapp.generated.resources.email
import muktokowlomcmp.composeapp.generated.resources.enter_address
import muktokowlomcmp.composeapp.generated.resources.enter_age
import muktokowlomcmp.composeapp.generated.resources.enter_date_of_birth
import muktokowlomcmp.composeapp.generated.resources.enter_email_address
import muktokowlomcmp.composeapp.generated.resources.enter_first_name
import muktokowlomcmp.composeapp.generated.resources.enter_last_name
import muktokowlomcmp.composeapp.generated.resources.enter_phone_number
import muktokowlomcmp.composeapp.generated.resources.enter_second_phone_number
import muktokowlomcmp.composeapp.generated.resources.first_name
import muktokowlomcmp.composeapp.generated.resources.ic_placeholder
import muktokowlomcmp.composeapp.generated.resources.last_name
import muktokowlomcmp.composeapp.generated.resources.password_update
import muktokowlomcmp.composeapp.generated.resources.phone_number
import muktokowlomcmp.composeapp.generated.resources.save
import muktokowlomcmp.composeapp.generated.resources.second_number
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.removeLastOrNull

private const val TAG = "EditProfileScreen"
@Composable
fun EditProfileScreen(navBackStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: EditProfileViewModel = koinViewModel()
    val emailAddress = viewModel.emailAddressState.collectAsStateWithLifecycle("")
    val userProfileImage = viewModel.userProfileImage.collectAsStateWithLifecycle("")

    val fullName = viewModel.userName.collectAsStateWithLifecycle("")
    val firstNameState = viewModel.firstNameState.collectAsStateWithLifecycle()
    val lastNameState = viewModel.lastNameState.collectAsStateWithLifecycle()
    val emailAddressState = viewModel.emailAddressState.collectAsStateWithLifecycle()
    val phoneNumberState = viewModel.phoneNumberState.collectAsStateWithLifecycle()
    val secondNumberState = viewModel.secondNumberState.collectAsStateWithLifecycle()
    val dateOfBirthState = viewModel.dateOfBirthState.collectAsStateWithLifecycle()
    val ageState = viewModel.ageStateFlow.collectAsStateWithLifecycle()
    val addressState = viewModel.addressState.collectAsStateWithLifecycle()

    val isValidSaveButton = viewModel.isValidationSaveButton.collectAsStateWithLifecycle(false)


    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            MyCustomAppBar(title = stringResource(Res.string.edit_profile), onBackPress = {
                try {
                    if (navBackStack.size > 1) {
                        navBackStack.removeLastOrNull()
                    }else {
                        rootBackStack.removeLastOrNull()
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }, editProfile = {})
        }) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Column(

                modifier = Modifier.fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .imePadding()
            ) {
                Column(
                    modifier = Modifier.weight(1f).verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    HeightGap(height = 20.dp)
                    AsyncImage(
                        contentDescription = null,
                        model = ImageRequest.Builder(context = LocalPlatformContext.current).size(100).data(userProfileImage.value).build(),
                        error = painterResource(Res.drawable.ic_placeholder),
                        placeholder = painterResource(Res.drawable.ic_placeholder),
                        modifier = Modifier.size(80.dp).clip(shape = CircleShape).clickable {
                            viewModel.takeProfileImageFromGallery = true
                        },
                        contentScale = ContentScale.Crop
                    )
                    HeightGap(height = 20.dp)
                    Text(
                        text = fullName.value,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    HeightGap(height = 20.dp)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.first_name),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_first_name),
                                text = firstNameState.value,
                                onValueChange = { firstNameInput ->
                                    viewModel.updateFirstName(firstNameInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.last_name),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_last_name),
                                text = lastNameState.value,
                                onValueChange = { lastNameInput ->
                                    viewModel.updateLastName(lastName = lastNameInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                    }

                    HeightGap(height = 10.dp)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.email),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_email_address),
                                text = emailAddressState.value,
                                onValueChange = { emailAddress ->
                                    viewModel.updateEmailAddress(emailAddress = emailAddress)
                                },
                                isPasswordInput = false,
                                readOnly = true,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.phone_number),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_phone_number),
                                text = phoneNumberState.value,
                                onValueChange = { phoneNumberInput ->
                                    viewModel.updatePhoneNumber(phoneNumber = phoneNumberInput)
                                },
                                isNumberType = true,
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                    }

                    HeightGap(height = 10.dp)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.second_number),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_second_phone_number),
                                text = secondNumberState.value,
                                onValueChange = { secondPhoneNumberInput ->
                                    viewModel.updateSecondPhoneNumber(secondNumber = secondPhoneNumberInput)
                                },
                                isPasswordInput = false,
                                isNumberType = true,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.date_of_birth),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_date_of_birth),
                                text = dateOfBirthState.value,
                                onValueChange = { dateOfBirthInput ->
                                    viewModel.updateDateOfBirth(dateOfBirth = dateOfBirthInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                isPasswordVisibility = true,
                                modifier = Modifier,
                                readOnly = true
                            ) {
                                print("click date of birth")
                                viewModel.isOpenDatePicker = true
                            }
                        }
                    }

                    HeightGap(height = 10.dp)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.age),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_age),
                                text = ageState.value,
                                onValueChange = { ageInput ->
                                    viewModel.updateAge(age = ageInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                isNumberType = true,
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.address),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = adjustedFontSize(10f),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_address),
                                text = addressState.value,
                                onValueChange = { addressInput ->
                                    viewModel.updateAddress(address = addressInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                modifier = Modifier,
                                isPasswordVisibility = true,
                            ) {}
                        }
                    }

                    HeightGap(height = 20.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MyCustomButton(
                            title = stringResource(Res.string.save),
                            modifier = Modifier.weight(1f),
                            onClickButton = {

                            },
                            isEnable = isValidSaveButton.value
                        )
                        WidthGap(width = 10.dp)
                        MyCustomButton(
                            backgroundColor = Color.Red,
                            title = stringResource(Res.string.password_update),
                            modifier = Modifier.weight(1f),
                            onClickButton = {
                                navBackStack.add(
                                    AppDestination.Dest.UpdatePassword(
                                        emailAddress = emailAddress.value
                                    )
                                )
                            },
                            isEnable = true
                        )
                    }
                }

            }
            }
        }

        
        if(viewModel.isOpenDatePicker){
            WheelDatePickerDialog(
                onDismissRequest = {
                    viewModel.isOpenDatePicker = false
                },
                onDateSelected = {localDate ->
                    viewModel.isOpenDatePicker = false
                    viewModel.updateDateOfBirth(dateOfBirth = localDate.toString())
                }
            )
        }




        // show gallery
    }
}


