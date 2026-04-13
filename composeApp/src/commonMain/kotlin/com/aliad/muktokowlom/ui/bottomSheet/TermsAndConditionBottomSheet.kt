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
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.terms_and_conditions
import muktokowlomcmp.composeapp.generated.resources.test
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionBottomSheet(
    privacyPolicyViewModel: PrivacyPolicyViewModel,
    onDismissRequest: () -> Unit,
) {

    val privacyPolicy = privacyPolicyViewModel.privacyPolicy.collectAsStateWithLifecycle()
    val privacyPolicyDetails = htmlToString(privacyPolicy.value?.description ?: "")

    ModalBottomSheet(onDismissRequest = {onDismissRequest.invoke()}){
        val coroutineScope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

        ModalBottomSheet(
            onDismissRequest = { onDismissRequest.invoke() },
            containerColor = MaterialTheme.colorScheme.surface,
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
                            contentDescription = null
                        )
                    }

                    WidthGap(width = 10.dp)

                    Text(
                        text = stringResource(Res.string.terms_and_conditions),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W500
                        )
                    )
                }


                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    if(privacyPolicyViewModel.isLoading){
                        WatchRunningAnimation(
                            clockColor = Color.Gray.copy(alpha = 0.1f),
                            handColor = Color.Gray,
                            clockSize = 30.dp
                        )
                    }else {
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .verticalScroll(state = rememberScrollState()).padding(16.dp)
                        ) {
                            Text(
                                text = privacyPolicyDetails,
                                modifier = Modifier.fillMaxSize(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }


            }

        }
    }
}