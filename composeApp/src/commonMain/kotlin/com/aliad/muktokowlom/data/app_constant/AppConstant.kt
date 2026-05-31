package com.aliad.muktokowlom.data.app_constant

import androidx.compose.ui.graphics.Color
import com.aliad.muktokowlom.ui.theme.bkashColor
import com.aliad.muktokowlom.ui.theme.nagodColor
import com.aliad.muktokowlom.ui.theme.visaColor
import com.aliad.muktokowlom.utils.MyCustomLogger
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.bkash_icon
import muktokowlomcmp.composeapp.generated.resources.mtb
import muktokowlomcmp.composeapp.generated.resources.nagod
import muktokowlomcmp.composeapp.generated.resources.trust_bank_seeklogo_3
import muktokowlomcmp.composeapp.generated.resources.upay
import muktokowlomcmp.composeapp.generated.resources.visa_svgrepo_com
import org.jetbrains.compose.resources.DrawableResource

private const val TAG = "AppConstant"
object AppConstant {

    fun paymentTypeToImage(type: String): DrawableResource {
        MyCustomLogger.logInfo(tag = TAG, message = "card type $type")
        return when (type) {
            "bKash-bKash" -> Res.drawable.bkash_icon
            "nagad" -> Res.drawable.nagod
            "TAP" -> Res.drawable.mtb
            "UPAY" -> Res.drawable.upay
            else -> Res.drawable.visa_svgrepo_com
        }
    }

    fun paymentTypeToColor(type: String): Color {
        return when (type) {
            "bKash-bKash" -> bkashColor
            "nagad" -> nagodColor
            "UPAY" -> visaColor
            else -> visaColor
        }
    }



    const val SELECT_LOCAL = "selectLocal"

    fun toBanglaDigits(input: String): String {
        val bnDigits = listOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
        return input.map {
            if (it.isDigit()) bnDigits[it - '0'] else it
        }.joinToString("")
    }
}