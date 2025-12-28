package com.aliad.muktokowlom.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.aliad.model.Category
import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination : NavKey {

    @Serializable
    object SignInScreen : AppDestination()

    @Serializable
    object HomeScreen : AppDestination()

    @Serializable
    object SignUpScreen : AppDestination()

    @Serializable
    object CategoryScreen : AppDestination()

    @Serializable
    data class CategoryWiseBook(val category: Category?= null) : AppDestination()
}