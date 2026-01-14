package com.aliad.presentation.signIn.di

import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.aliad.presentation.signIn.ui.datastore.DataStore
import com.aliad.presentation.signIn.ui.signin.SignInViewModel
import com.aliad.presentation.signIn.ui.signup.SignUpViewModel
import com.aliad.presentation.signIn.ui.storytype.StoryTypeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        SignInViewModel(loginUseCase = get())
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
        DataStore(
            saveStringData = get(),
            getStringData = get(),
            saveIntData = get(),
            getIntData = get(),
            saveBoolData = get(),
            getBoolData = get()
        )
    }
}