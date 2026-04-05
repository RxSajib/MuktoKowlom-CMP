package com.aliad.muktokowlom.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.aliad.model.BookItem
import com.aliad.model.Category
import com.aliad.model.MyBookItem
import com.aliad.repository.StoryType
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
    data object Splash{
        @Serializable
        data object SplashScreen : AppDestination()
    }


    @Serializable
    data class Dest(val firstDestName : String) : AppDestination() {

        @Serializable
        data object MostPopular : AppDestination()

        @Serializable
        data object NewReleaseStory : AppDestination()

        @Serializable
        data object AllReleaseStory : AppDestination()

        @Serializable
        data object PublishedPendingStory : AppDestination()

        @Serializable
        data object SearchStoryResult : AppDestination()

        @Serializable
        data object UpdatePassword : AppDestination()

        @Serializable
        data class StoryTypeWiseBook(
            val typeName : String? = null
        ) : AppDestination()

        @Serializable
        data object SubscriptionHistory : AppDestination()

        @Serializable
        data object UploadStories: AppDestination()

        @Serializable
        data object PrivacyPolicy : AppDestination()

        @Serializable
        data object Premium : AppDestination()

        @Serializable
        data object CategoryWiseBook : AppDestination()

        @Serializable
        data object EditProfile : AppDestination()

        @Serializable
        data object EarningHistory : AppDestination()


        @Serializable
        data object StoryView: AppDestination()
    }

    @Serializable
    data class Auth(val firstDestName : String) : AppDestination(){

        @Serializable
        object SignUp : AppDestination()

        @Serializable
        object SignIn : AppDestination()

        @Serializable
        data object RecoveryPassword : AppDestination()

        @Serializable
        data class Otp(val emailOrPhoneNumber : String = "") : AppDestination()
    }















    @Serializable
    object HomeScreen : AppDestination()



    @Serializable
    object CategoryScreen : AppDestination()





    @Serializable
    data class StoryDetails(val myBookItem: MyBookItem) : AppDestination()

    @Serializable
    data object Profile : AppDestination()







}