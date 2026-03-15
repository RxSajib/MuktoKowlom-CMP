package com.aliad.muktokowlom.data.app_constant

import com.aliad.muktokowlom.data.enum.PaymentType
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.bkash_bkash_logo
import muktokowlomcmp.composeapp.generated.resources.membership_valid_days
import muktokowlomcmp.composeapp.generated.resources.nagad_logo
import muktokowlomcmp.composeapp.generated.resources.visa_inc_logo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

object AppConstant {

    fun paymentTypeToImage(type: String): DrawableResource {
        return when (type) {
            PaymentType.bKash.name -> Res.drawable.bkash_bkash_logo
            PaymentType.Nagad.name -> Res.drawable.nagad_logo
            else -> Res.drawable.visa_inc_logo
        }
    }
}