package com.aliad.muktokowlom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliad.muktokowlom.ui.navigation.root.RootNavigation
import com.aliad.muktokowlom.utils.Localization
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {
        RootNavigation()
    }
}