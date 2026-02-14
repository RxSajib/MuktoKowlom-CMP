package com.sajib.data.appConstant

import kotlinx.datetime.LocalDateTime

object AppConstant {
    val emailRegex =
        Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+\$")

    const val USER_EMAIL_ADDRESS = "userEmailAddress"
    const val ACCESS_TOKEN = "accessToken"
    const val USER_NAME = "userName"
    const val USER_PHONE = "userLastName"
    const val USER_PROFILE_IMAGE = "userProfileImage"
    const val USER_REGISTER_DATE = "userRegisterDate"


    fun formatDate(input: String): String {
        if (input.isNullOrBlank()) return ""

        return try {
            val isoString = input.replace(" ", "T")
            val dateTime = LocalDateTime.parse(isoString)

            val hour12 = if (dateTime.hour % 12 == 0) 12 else dateTime.hour % 12
            val amPm = if (dateTime.hour >= 12) "PM" else "AM"

            val monthShort = dateTime.month.name
                .lowercase()
                .replaceFirstChar { it.uppercase() }
                .take(3)

            "${dateTime.dayOfMonth} $monthShort ${dateTime.year}, " +
                    "${hour12.toString().padStart(2, '0')}:" +
                    "${dateTime.minute.toString().padStart(2, '0')} $amPm"

        } catch (e: Exception) {
            ""
        }
    }
}