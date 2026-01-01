package com.aliad.di

import com.aliad.usecase.CategoryUseCase
import com.aliad.usecase.CategoryWiseBookUseCase
import com.aliad.usecase.DashBoardUseCase
import com.aliad.usecase.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        LoginUseCase(
            accountRepository = get()
        )
    }

    factory {
        CategoryUseCase(
            categoryRepository = get()
        )
    }

    factory {
        CategoryWiseBookUseCase(
           categoryRepository = get()
        )
    }

    factory {
        DashBoardUseCase(dashBordRepository = get())
    }
}