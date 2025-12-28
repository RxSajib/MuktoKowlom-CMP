package com.aliad.di

import com.aliad.usecase.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        LoginUseCase(
            accountRepository = get()
        )
    }
}