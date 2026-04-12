package com.aliad.muktokowlom.utils

import kotlinx.datetime.LocalDate

object AppHelper {

    fun LocalDate.toUSFormatWithMonth(): String {
        try {
            val monthName = this.month.name.lowercase().replaceFirstChar { it.uppercase() }
            return "$monthName $dayOfMonth, $year"
        }catch (e : Exception){
            return ""
        }

    }
}