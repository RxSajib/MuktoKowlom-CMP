package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedOtpInput(
    otpLength: Int = 5,
    onOtpComplete: (String) -> Unit
) {
    var otp by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        value = otp,
        onValueChange = { value ->
            val filtered = value.filter { it.isDigit() }

            if (filtered.length <= otpLength) {
                otp = filtered

                if (filtered.length == otpLength) {
                    onOtpComplete(filtered)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth(),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,// Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth(),

            ) {
                repeat(otpLength) { index ->
                    val isFocused = otp.length == index
                    val char = otp.getOrNull(index)?.toString() ?: ""

                    val borderColor by animateColorAsState(
                        targetValue = when {
                            isFocused -> MaterialTheme.colorScheme.inverseSurface
                            char.isNotEmpty() -> onPrimaryLight
                            else -> Color.Gray
                        },
                        label = ""
                    )

                    val scale by animateFloatAsState(
                        targetValue = if (isFocused) 1.1f else 1f,
                        label = ""
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 3.dp)
                            .size(40.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }

                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = CircleShape
                            )
                    ) {
                        AnimatedContent(
                            targetState = char,
                            transitionSpec = {
                                scaleIn() togetherWith fadeOut()
                            },
                            label = ""
                        ) { value ->
                            Text(
                                text = value,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
@Preview
fun OtpInputPreview(){
    AnimatedOtpInput{

    }
}
