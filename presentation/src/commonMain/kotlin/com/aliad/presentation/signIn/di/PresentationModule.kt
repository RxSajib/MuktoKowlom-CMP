package com.aliad.presentation.signIn.di

import com.aliad.presentation.signIn.ui.allReleaseStory.AllReleaseStoryViewModel
import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.editProfile.EditProfileViewModel
import com.aliad.presentation.signIn.ui.mostPopularStory.MostPopularStoryViewModel
import com.aliad.presentation.signIn.ui.newReleaseStory.NewReleaseStoryViewModel
import com.aliad.presentation.signIn.ui.otpVerification.OtpVerificationViewModel
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.aliad.presentation.signIn.ui.profile.ProfileViewModel
import com.aliad.presentation.signIn.ui.recoveryPassword.RecoveryPasswordViewModel
import com.aliad.presentation.signIn.ui.search.SearchViewModel
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

    viewModel {
        CategoryWiseBookViewModel(categoryWiseBookUseCase = get())
    }

    viewModel {
        DashBoardViewModel(dashBoardUseCase = get())
    }

    viewModel {
        StoryTypeViewModel(storyTypeUseCase = get())
    }
    viewModel {
        StoryDetailsViewModel()
    }
    viewModel {
        ProfileViewModel()
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
        RecoveryPasswordViewModel()
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

    viewModel {
        AllReleaseStoryViewModel(
            storyTypeUseCase = get()
        )
    }

    viewModel {
        NewReleaseStoryViewModel(
            storyTypeUseCase = get()
        )
    }

    viewModel {
        MostPopularStoryViewModel(
            storyTypeUseCase = get()
        )
    }
}