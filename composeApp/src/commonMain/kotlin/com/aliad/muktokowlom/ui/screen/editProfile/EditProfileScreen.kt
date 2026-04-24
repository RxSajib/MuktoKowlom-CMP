package com.aliad.muktokowlom.ui.screen.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.screen.component.WheelDatePickerDialog
import com.aliad.muktokowlom.ui.screen.component.WidthGap
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

@Composable
fun EditProfileScreen(navBackStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: EditProfileViewModel = koinViewModel()
    val dataStoreViewModel : DataStoreViewModel = koinViewModel()

    val userEmailAddress by dataStoreViewModel.getStringData(key = AppConstant.USER_EMAIL_ADDRESS).collectAsStateWithLifecycle(null)
    val userProfileImage by dataStoreViewModel.getStringData(key = AppConstant.USER_PROFILE_IMAGE).collectAsStateWithLifecycle(null)
    val userPhoneNumber by dataStoreViewModel.getStringData(key = AppConstant.USER_PHONE).collectAsStateWithLifecycle(null)
    val userName by dataStoreViewModel.getStringData(key = AppConstant.USER_NAME).collectAsStateWithLifecycle(null)
    val userSecondNumber by dataStoreViewModel.getStringData(key = AppConstant.USER_SECOND_NUMBER).collectAsStateWithLifecycle(null)
    val userDateOfBirth by dataStoreViewModel.getStringData(key = AppConstant.USER_DATE_OF_BIRTH).collectAsStateWithLifecycle(null)
    val userAge by dataStoreViewModel.getStringData(key = AppConstant.USER_AGE).collectAsStateWithLifecycle(null)
    val userAddress by dataStoreViewModel.getStringData(key = AppConstant.USER_ADDRESS).collectAsStateWithLifecycle(null)

    val userID by dataStoreViewModel.getIntData(key = AppConstant.USER_ID).collectAsStateWithLifecycle(null)
    val token by dataStoreViewModel.getStringData(key = AppConstant.ACCESS_TOKEN).collectAsStateWithLifecycle(null)

    print("userID ${userID.toString()}")
    print("token ${token.toString()}")

    val parts = userName?.trim()?.split(" ")
    val firstName = parts?.firstOrNull() ?: ""
    val lastName = parts?.lastOrNull() ?: ""

    val firstNameState = viewModel.firstNameState.collectAsStateWithLifecycle()
    val lastNameState = viewModel.lastNameState.collectAsStateWithLifecycle()
    val emailAddressState = viewModel.emailAddressState.collectAsStateWithLifecycle()
    val phoneNumberState = viewModel.phoneNumberState.collectAsStateWithLifecycle()
    val secondNumberState = viewModel.secondNumberState.collectAsStateWithLifecycle()
    val dateOfBirthState = viewModel.dateOfBirthState.collectAsStateWithLifecycle()
    val ageState = viewModel.ageStateFlow.collectAsStateWithLifecycle()
    val addressState = viewModel.addressState.collectAsStateWithLifecycle()

    val isValidSaveButton = viewModel.isValidationSaveButton.collectAsStateWithLifecycle(false)



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

        Surface(
            modifier = Modifier
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
                        model = userProfileImage,
                        error = painterResource(Res.drawable.ic_placeholder),
                        placeholder = painterResource(Res.drawable.ic_placeholder),
                        modifier = Modifier.size(80.dp).clip(shape = CircleShape).clickable{
                            viewModel.takeProfileImageFromGallery = true
                        },
                        contentScale = ContentScale.Crop
                    )
                    HeightGap(height = 20.dp)
                    Text(
                        text = userName?: "",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
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
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isPasswordVisibility = true,
                            ){}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.last_name),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isPasswordVisibility = true,
                            ){}
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
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isVisiblePasswordChange = {},
                                isPasswordVisibility = true,
                            ){}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.phone_number),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
                                )
                            )
                            HeightGap(height = 4.dp)
                            MyCustomInputFiled(
                                placeHolderText = stringResource(Res.string.enter_phone_number),
                                text = phoneNumberState.value,
                                onValueChange = { phoneNumberInput ->
                                    viewModel.updatePhoneNumber(phoneNumber = phoneNumberInput)
                                },
                                isPasswordInput = false,
                                isVisiblePasswordChange = {},
                                isPasswordVisibility = true,
                            ){}
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
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isVisiblePasswordChange = {},
                                isPasswordVisibility = true,
                            ){}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.date_of_birth),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                readOnly = true
                            ){
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
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isPasswordVisibility = true,
                            ){}
                        }
                        WidthGap(width = 10.dp)

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(Res.string.address),
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
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
                                isPasswordVisibility = true,
                            ){}
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
                                navBackStack.add(AppDestination.Dest.UpdatePassword(
                                    emailAddress = userEmailAddress?: "",
                                ))
                            },
                            isEnable = true
                        )
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


