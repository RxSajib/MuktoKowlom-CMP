package com.aliad.presentation.signIn.di

import androidx.lifecycle.SavedStateHandle
import com.aliad.presentation.signIn.ui.FavoriteStory.FavoriteStoryViewModel
import com.aliad.presentation.signIn.ui.allReleaseStory.AllReleaseStoryViewModel
import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import com.aliad.presentation.signIn.ui.changeLanguage.ChangeLanguageViewModel
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.editProfile.EditProfileViewModel
import com.aliad.presentation.signIn.ui.mostPopularStory.MostPopularStoryViewModel
import com.aliad.presentation.signIn.ui.newReleaseStory.NewReleaseStoryViewModel
import com.aliad.presentation.signIn.ui.otpVerification.OtpVerificationViewModel
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.aliad.presentation.signIn.ui.profile.ProfileViewModel
import com.aliad.presentation.signIn.ui.recoveryPassword.RecoveryPasswordViewModel
import com.aliad.presentation.signIn.ui.resetPassword.ResetPasswordViewModel
import com.aliad.presentation.signIn.ui.search.SearchViewModel
import com.aliad.presentation.signIn.ui.searchStoryResult.SearchStoryResultViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.aliad.presentation.signIn.ui.signin.SignInViewModel
import com.aliad.presentation.signIn.ui.signup.SignUpViewModel
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import com.aliad.presentation.signIn.ui.storytype.StoryTypeViewModel
import com.aliad.presentation.signIn.ui.subscriptionHistory.SubscriptionHistoryViewModel
import com.aliad.presentation.signIn.ui.subscriptionPlan.SubscriptionPlanViewModel
import com.aliad.presentation.signIn.ui.updatePassword.UpdatePasswordViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SharedViewModel()
    }

    viewModel {
        DataStoreViewModel(
            saveStringData = get(),
            getStringData = get(),
            saveIntData = get(),
            getIntData = get(),
            saveBoolData = get(),
            getBoolData = get(),
            deleteStringDataUseCase = get(),
            deleteIntDataUseCase = get(),
            deleteBoolDataUseCase = get()
        )
    }

    viewModel {
        SignInViewModel(loginUseCase = get(), saveStringData = get(), saveIntData = get())
    }

    viewModel {
        SignUpViewModel(
            signUpUseCase = get()
        )
    }

    viewModel {
        CategoryViewModel(categoryUseCase = get())
    }

    viewModel { (stateHandle: SavedStateHandle) ->
        CategoryWiseBookViewModel(
            categoryWiseBookUseCase = get(),
            savedStateHandle = stateHandle
        )
    }

    viewModel {
        DashBoardViewModel(dashBoardUseCase = get())
    }

    viewModel {
        StoryTypeViewModel(storyTypeUseCase = get())
    }
    viewModel {
        StoryDetailsViewModel(
            storyDetailsUseCase = get()
        )
    }
    viewModel {
        ProfileViewModel(
            deleteAccountUseCase = get()
        )
    }
    viewModel {
        EditProfileViewModel()
    }
    viewModel {
        SubscriptionPlanViewModel(
            premiumPlanUseCase = get()
        )
    }
    viewModel {
        PrivacyPolicyViewModel(privacyPolicyUseCase = get())
    }

    viewModel {
        RecoveryPasswordViewModel(
            resetPasswordUseCase = get()
        )
    }

    viewModel {
        UpdatePasswordViewModel()
    }

    viewModel {
        SearchViewModel(popularSearchUseCase = get())
    }

    viewModel {
        SubscriptionHistoryViewModel(subscriptionHistoryUseCase = get())
    }

    viewModel {
        OtpVerificationViewModel(
            otpVerificationUseCase = get(),
            saveStringData = get(),
            saveIntData = get()
        )
    }

    viewModel {(saveStateHandle : SavedStateHandle) ->
        AllReleaseStoryViewModel(
            allReleaseUseCase = get(),
            savedStateHandle = saveStateHandle
        )
    }

    viewModel {(savestateHandle : SavedStateHandle) ->
        NewReleaseStoryViewModel(
            storyTypeUseCase = get(),
            savedStateHandle = savestateHandle
        )
    }

    viewModel {(saveStateHandle : SavedStateHandle) ->
        MostPopularStoryViewModel(
            storyTypeUseCase = get(),
            savedStateHandle = saveStateHandle
        )
    }

    viewModel {
        FavoriteStoryViewModel()
    }

    viewModel {(saveStateHandle : SavedStateHandle) ->
        SearchStoryResultViewModel(
            searchBookUseCase = get(),
            savedStateHandle = saveStateHandle
        )
    }

    viewModel {
        ResetPasswordViewModel()
    }

    viewModel {
        ChangeLanguageViewModel()
    }
}