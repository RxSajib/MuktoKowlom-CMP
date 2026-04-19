package com.aliad.muktokowlom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.aliad.helper.SnackBarEvent
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.utils.Localization
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.launguage_change_success
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {

            val lifecycle = LocalLifecycleOwner.current
            val dataStoreViewModel: DataStoreViewModel = koinViewModel()
            val localization = koinInject<Localization>()
            val localIso =
                dataStoreViewModel.getStringData(key = AppConstant.SELECT_LOCAL)
                    .collectAsStateWithLifecycle(null)

            key(localIso.value) {
                App()
            }

            val success = stringResource(Res.string.launguage_change_success)

            LaunchedEffect(localIso.value) {

                localIso.value?.let {
                    localization.setLocal(iso = it)


                    /*SnackBarEvent.save(
                        details = SnackBarDetails(
                            details = success,
                            show = true,
                            leftIcon = Icons.Default.LockOpen
                        )
                    )*/


                }

            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}