package com.aliad.muktokowlom.ui.screen.changeLaunguage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.LanguageItem
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.utils.Localization
import com.aliad.presentation.signIn.ui.changeLanguage.ChangeLanguageViewModel
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.choose_language
import muktokowlomcmp.composeapp.generated.resources.launguage_change_success
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.update
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.get

@Composable
fun ChangeLanguageScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val chooseLanguageViewModel : ChangeLanguageViewModel = koinViewModel()
    val languages : Localization = koinInject()
    val dataStoreViewModel : DataStoreViewModel = koinViewModel()
    val successData = stringResource(Res.string.launguage_change_success)

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.choose_language),
                    onBackPress = {
                        rootBackStack.removeLastOrNull()
                    },
                    editProfile = {}
                )
            }
        ) {innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp).imePadding()) {


                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(chooseLanguageViewModel.languageData.size) { position ->

                            LanguageItem(
                                data = chooseLanguageViewModel.languageData[position],
                                isSelected =  chooseLanguageViewModel.selectedPosition == position,
                                onItemSelect = {
                                    chooseLanguageViewModel.selectedPosition = position
                                    chooseLanguageViewModel.selectedLanguage = it
                                })


                            if (chooseLanguageViewModel.languageData.indexOf(chooseLanguageViewModel.languageData[position]) != chooseLanguageViewModel.languageData.lastIndex) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                HeightGap(height = 20.dp)
                MyCustomButton(
                    title = stringResource(Res.string.update),
                    modifier = Modifier,
                    onClickButton = {

                        languages.setLocal(chooseLanguageViewModel.selectedLanguage.code?: "en")
                        dataStoreViewModel.saveStringData(key = AppConstant.SELECT_LOCAL, chooseLanguageViewModel.selectedLanguage.code?: "en")

                        SnackBarEvent.save(
                            details = SnackBarDetails(
                                details = successData,
                                show = true,
                                leftIcon = Icons.Default.LockOpen
                            )
                        )

                        rootBackStack.removeLastOrNull()
                    },
                    isEnable = true,
                    showProgress = false
                )

                HeightGap(height = 10.dp)

            }
        }
    }
}