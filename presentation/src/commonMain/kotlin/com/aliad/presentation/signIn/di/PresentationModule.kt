package com.aliad.presentation.signIn.di

import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.editProfile.EditProfileViewModel
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.aliad.presentation.signIn.ui.profile.ProfileViewModel
import com.aliad.presentation.signIn.ui.recoveryPassword.RecoveryPasswordViewModel
import com.aliad.presentation.signIn.ui.signin.SignInViewModel
import com.aliad.presentation.signIn.ui.signup.SignUpViewModel
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import com.aliad.presentation.signIn.ui.storytype.StoryTypeViewModel
import com.aliad.presentation.signIn.ui.subscriptionPlan.SubscriptionPlanViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    
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
        SignInViewModel(loginUseCase = get(), saveStringData = get())
    }

    viewModel {
        SignUpViewModel()
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
}