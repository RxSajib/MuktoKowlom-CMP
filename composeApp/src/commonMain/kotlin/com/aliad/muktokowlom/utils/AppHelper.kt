package com.aliad.muktokowlom.utils

import com.aliad.muktokowlom.utils.enum.SubscriptionType
import kotlinx.datetime.LocalDate
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.best_deal
import muktokowlomcmp.composeapp.generated.resources.icon_diamond
import muktokowlomcmp.composeapp.generated.resources.icon_energy_flash
import muktokowlomcmp.composeapp.generated.resources.icon_fire
import muktokowlomcmp.composeapp.generated.resources.icon_star_favorite
import muktokowlomcmp.composeapp.generated.resources.most_popular
import muktokowlomcmp.composeapp.generated.resources.smart_chose
import muktokowlomcmp.composeapp.generated.resources.start_writing_your_story
import muktokowlomcmp.composeapp.generated.resources.starter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

object AppHelper {

    fun LocalDate.toUSFormatWithMonth(): String {
        try {
            val monthName = this.month.name.lowercase().replaceFirstChar { it.uppercase() }
            return "$monthName $dayOfMonth, $year"
        }catch (e : Exception){
            return ""
        }

    }

    fun getPackageInfoBasedOnPackage(type: String): StringResource {
        return when (type) {
            SubscriptionType.Daily.name -> Res.string.starter
            SubscriptionType.Weekly.name -> Res.string.most_popular
            SubscriptionType.HalfMonthly.name -> Res.string.best_deal
            SubscriptionType.Monthly.name -> Res.string.smart_chose
            else -> Res.string.smart_chose
        }
    }

    fun getPackageIconBasedOnPackageBadge(type: String) : DrawableResource {
        return when (type) {
            SubscriptionType.HalfMonthly.name -> Res.drawable.icon_fire
            SubscriptionType.Weekly.name -> Res.drawable.icon_star_favorite
            SubscriptionType.Daily.name -> Res.drawable.icon_energy_flash
            SubscriptionType.Monthly.name -> Res.drawable. icon_diamond
            else -> Res.drawable.icon_energy_flash
        }
    }

    fun getPackageTextBasedOnPackageBadge(type : String) : String {
        return when(type){
            "Weekly Package" -> SubscriptionType.Weekly.name
            "Half-Monthly Package" -> SubscriptionType.HalfMonthly.name
            "Monthly Package" -> SubscriptionType.Monthly.name
            "Daily Package" -> SubscriptionType.Daily.name
            else -> SubscriptionType.Daily.name
        }
    }

    fun getPackageLottieVisibility(type : String) : Boolean{

        return when(type) {
            SubscriptionType.HalfMonthly.name -> true
            SubscriptionType.Weekly.name -> false
            SubscriptionType.Daily.name -> false
            SubscriptionType.Monthly.name -> true
            else -> false
        }
    }
}