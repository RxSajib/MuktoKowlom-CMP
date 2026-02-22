package com.aliad.muktokowlom.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.ui.bottomSheet.DeleteAccountBottomSheet
import com.aliad.muktokowlom.ui.bottomSheet.LogoutBottomSheet
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomMenu
import com.aliad.muktokowlom.ui.screen.component.UserInfo
import com.aliad.muktokowlom.ui.screen.component.UserInfoItem
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.profile.ProfileViewModel
import com.sajib.data.appConstant.AppConstant
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.basic_info
import muktokowlomcmp.composeapp.generated.resources.calender_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.copyright_muktokowlom_all_right_reserved
import muktokowlomcmp.composeapp.generated.resources.delete_account
import muktokowlomcmp.composeapp.generated.resources.delete_account_details
import muktokowlomcmp.composeapp.generated.resources.delete_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.earningHistory
import muktokowlomcmp.composeapp.generated.resources.earningHistoryDetails
import muktokowlomcmp.composeapp.generated.resources.edit
import muktokowlomcmp.composeapp.generated.resources.electricity_energy_off_on_power_switch_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.logout
import muktokowlomcmp.composeapp.generated.resources.logout_details
import muktokowlomcmp.composeapp.generated.resources.logout_success
import muktokowlomcmp.composeapp.generated.resources.mail_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.money_cash
import muktokowlomcmp.composeapp.generated.resources.phone_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.policy
import muktokowlomcmp.composeapp.generated.resources.premium
import muktokowlomcmp.composeapp.generated.resources.premium_details
import muktokowlomcmp.composeapp.generated.resources.premium_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import muktokowlomcmp.composeapp.generated.resources.privacy_policy_details
import muktokowlomcmp.composeapp.generated.resources.profile
import muktokowlomcmp.composeapp.generated.resources.subscription_history
import muktokowlomcmp.composeapp.generated.resources.subscription_history_details
import muktokowlomcmp.composeapp.generated.resources.ticket
import muktokowlomcmp.composeapp.generated.resources.upload_cloud_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.upload_stories
import muktokowlomcmp.composeapp.generated.resources.upload_stories_details
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(backStack: NavBackStack<NavKey>) {

    val viewModel: ProfileViewModel = koinViewModel()
    val dataStoreViewModel : DataStoreViewModel = koinViewModel()
    val userName = dataStoreViewModel.getStringData(key = AppConstant.USER_NAME).collectAsStateWithLifecycle(null)
    val userEmailAddress = dataStoreViewModel.getStringData(key = AppConstant.USER_EMAIL_ADDRESS).collectAsStateWithLifecycle(null)
    val userProfileImage = dataStoreViewModel.getStringData(key = AppConstant.USER_PROFILE_IMAGE).collectAsStateWithLifecycle(null)
    val userPhoneNumber = dataStoreViewModel.getStringData(key = AppConstant.USER_PHONE).collectAsStateWithLifecycle(null)
    val userRegisterDate = dataStoreViewModel.getStringData(key = AppConstant.USER_REGISTER_DATE).collectAsStateWithLifecycle(null)
    val logoutText = stringResource(Res.string.logout_success)

    Scaffold(
        topBar = {
            MyCustomAppBar(onBackPress = {
                backStack.remove(AppDestination.Profile)
            }, title = stringResource(Res.string.profile), editProfile = {})
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).verticalScroll(state = rememberScrollState())
                .padding(16.dp)
        ) {

            UserInfo(
                userName = userName.value?: "",
                emailAddress = userEmailAddress.value?: "",
                userProfileImage = userProfileImage.value?: "",
                publishedStoryCount = 45,
                pendingStoryCount = 52,
                joinedSince = AppConstant.formatDate(input = userRegisterDate.value?: ""),
            )

            HeightGap(height = 10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.basic_info),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W500
                    )
                )
                Text(
                    text = stringResource(Res.string.edit),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White
                    ),
                    modifier = Modifier.clip(shape = CircleShape)
                        .background(color = MaterialTheme.colorScheme.primary).clickable {
                            backStack.add(AppDestination.EditProfile)
                        }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
            HeightGap(height = 5.dp)
            UserInfoItem(
                icon = painterResource(Res.drawable.phone_svgrepo_com),
                title = userPhoneNumber.value?: ""
            )
            UserInfoItem(
                icon = painterResource(Res.drawable.mail_svgrepo_com),
                title = userEmailAddress.value?: ""
            )
            UserInfoItem(
                icon = painterResource(Res.drawable.calender_svgrepo_com),
                title = AppConstant.formatDate(input = userRegisterDate.value?: ""),
                isDivider = false
            )
            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.upload_stories),
                details = stringResource(Res.string.upload_stories_details),
                painter = painterResource(Res.drawable.upload_cloud_svgrepo_com)
            ) {
                backStack.add(AppDestination.UploadStories)
            }
            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.premium),
                details = stringResource(Res.string.premium_details),
                painter = painterResource(Res.drawable.premium_svgrepo_com)
            ) {
                backStack.add(AppDestination.Premium)
            }

            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.subscription_history),
                details = stringResource(Res.string.subscription_history_details),
                painter = painterResource(Res.drawable.ticket)
            ) {
                backStack.add(AppDestination.SubscriptionHistory)
            }

            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.earningHistory),
                details = stringResource(Res.string.earningHistoryDetails),
                painter = painterResource(Res.drawable.money_cash)
            ) {
                backStack.add(AppDestination.EarningHistory)
            }
            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.privacy_policy),
                details = stringResource(Res.string.privacy_policy_details),
                painter = painterResource(Res.drawable.policy)
            ) {
                backStack.add(AppDestination.PrivacyPolicy)
            }

            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.delete_account),
                details = stringResource(Res.string.delete_account_details),
                painter = painterResource(Res.drawable.delete_svgrepo_com)
            ) {
                viewModel.deleteAccountDialogShow = true
            }

            HeightGap(height = 10.dp)

            MyCustomMenu(
                modifier = Modifier,
                title = stringResource(Res.string.logout),
                details = stringResource(Res.string.logout_details),
                painter = painterResource(Res.drawable.electricity_energy_off_on_power_switch_svgrepo_com)
            ) {
                viewModel.logoutDialogShow = true
            }

            HeightGap(height = 10.dp)

            Text(
                text = stringResource(Res.string.copyright_muktokowlom_all_right_reserved),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
    if (viewModel.logoutDialogShow) {
        LogoutBottomSheet(onDismissRequest = {
            viewModel.logoutDialogShow = false
        }, logoutButtonClick = {
            viewModel.logoutDialogShow = false
            dataStoreViewModel.deleteUser()
            backStack.clear()
            backStack.add(AppDestination.SignInScreen)


            SnackBarEvent.save(
                details = SnackBarDetails(
                    details = logoutText,
                    show = true,
                    leftIcon = Icons.Default.LockOpen
                )
            )
        })
    }

    if (viewModel.deleteAccountDialogShow) {
        DeleteAccountBottomSheet(deleteAccountButtonClick = {
            viewModel.deleteAccountDialogShow = false
        }) {
            viewModel.deleteAccountDialogShow = false
        }
    }
}

