package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.digitalia.compose.htmlconverter.htmlToString
import com.aliad.model.PrivacyPolicy
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.utils.getStoryData
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.aliad.presentation.utils.UiState
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyBottomSheet(
    privacyPolicyViewModel: PrivacyPolicyViewModel,
    onDismissRequest: () -> Unit
) {

    val privacyPolicy = privacyPolicyViewModel.privacyPolicy.collectAsStateWithLifecycle()
    val viewModel : DataStoreViewModel = koinViewModel()
    val selectLn = viewModel.getStringData(key = AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")



    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        containerColor = MaterialTheme.colorScheme.inversePrimary,
        sheetState = sheetState,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest.invoke()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint =  MaterialTheme.colorScheme.primary
                    )
                }

                WidthGap(width = 10.dp)

                Text(
                    text = stringResource(Res.string.privacy_policy),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }


            Box(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                when(privacyPolicy.value){
                    is UiState.Loading -> {
                        WatchRunningAnimation(
                            clockColor = Color.Gray.copy(alpha = 0.1f),
                            handColor = Color.Gray,
                            clockSize = 30.dp
                        )
                    }
                    is UiState.Success -> {
                        val data = (privacyPolicy.value as UiState.Success<PrivacyPolicy>).data
                         Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(state = rememberScrollState()).padding(16.dp)
                    ) {
                        Text(
                            text = getTitle(selectLn = selectLn.value, title = htmlToString(data.description), titleBn = htmlToString(data.descriptionBn)) ,
                            modifier = Modifier.fillMaxSize(),
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    }
                    is UiState.Error -> {
                        ServerError {
                          privacyPolicyViewModel.getPrivacyPolicy()
                        }
                    }
                }
            }


        }

    }
}

