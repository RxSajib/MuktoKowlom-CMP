package com.aliad.muktokowlom.di

import com.aliad.di.dataModule
import com.aliad.di.domainModule
import com.aliad.muktokowlom.platform.localModule

import com.aliad.muktokowlom.platform.platformModule
import com.aliad.presentation.signIn.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

val platformKoinModule: Module by lazy { platformModule() }
 val localModule: Module by lazy { localModule() }

val commonModule = listOf(
    dataModule,
    domainModule,
    presentationModule,
    platformKoinModule,
    localModule
)

fun initKoin(koinAppDeclaration: KoinAppDeclaration = {}){
    startKoin {
        koinAppDeclaration()
        modules(commonModule)
    }
}