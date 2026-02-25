package com.aliad.muktokowlom.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.aliad.model.BookItem
import com.aliad.model.Category
import com.aliad.model.MyBookItem
import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination : NavKey {

    @Serializable
    data object BottomAppBar : AppDestination() {

        @Serializable
        data object DashBoard : AppDestination()

        @Serializable
        data object Category : AppDestination()

        @Serializable
        data object Search : AppDestination()

        @Serializable
        data object FavoriteStory : AppDestination()

        @Serializable
        data object Profile : AppDestination()
    }


    @Serializable
    data class Dest(val firstDestName : String) : AppDestination() {

        @Serializable
        data object Premium : AppDestination()

        @Serializable
        data object CategoryWiseBook : AppDestination()

        @Serializable
        data object EditProfile : AppDestination()

        @Serializable
        data object EarningHistory : AppDestination()
    }

    @Serializable
    data class OtpView(val emailOrPhoneNumber : String = "") : AppDestination()

    @Serializable
    data object RecoveryPassword : AppDestination()

    @Serializable
    data object SplashScreen : AppDestination()

    @Serializable
    data object UploadStories: AppDestination()

    @Serializable
    data object SubscriptionHistory : AppDestination()



    @Serializable
    object SignInScreen : AppDestination()

    @Serializable
    object HomeScreen : AppDestination()

    @Serializable
    object SignUpScreen : AppDestination()

    @Serializable
    object CategoryScreen : AppDestination()



    @Serializable
    data class StoryTypeWiseBook(val typeName : String?= null) : AppDestination()

    @Serializable
    data class StoryDetails(val myBookItem: MyBookItem) : AppDestination()

    @Serializable
    data object Profile : AppDestination()





    @Serializable
    data object PrivacyPolicy : AppDestination()

}