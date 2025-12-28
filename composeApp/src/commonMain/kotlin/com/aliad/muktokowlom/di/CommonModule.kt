package com.aliad.muktokowlom.di

import com.aliad.di.dataModule
import com.aliad.di.domainModule
import com.aliad.presentation.signIn.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

val commonModule = listOf(
    dataModule,
    domainModule,
    presentationModule
)

fun initKoin(koinAppDeclaration: KoinAppDeclaration = {}){
    startKoin {
        koinAppDeclaration()
        modules(commonModule)
    }
}