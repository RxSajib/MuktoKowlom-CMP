package com.aliad.muktokowlom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliad.muktokowlom.utils.Localization
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {

            val dataStoreViewModel: DataStoreViewModel = koinViewModel()
            val localization = koinInject<Localization>()
            val localIso =
                dataStoreViewModel.getStringData(key = com.aliad.muktokowlom.data.app_constant.AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")

            key(localIso.value) {
                App()
            }


            LaunchedEffect(localIso.value) {
                localization.setLocal(iso = localIso.value)
                print("local ${localIso.value}")
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}