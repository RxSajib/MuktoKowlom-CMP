package com.aliad.di

import com.aliad.dataSource.RemoteDataSources
import com.aliad.pager.CategoryWiseBookPagingSource
import com.aliad.repository.AccountRepository
import com.aliad.repository.CategoryRepository
import com.aliad.repository.DashBordRepository
import com.aliad.repository.StoryType
import com.aliad.repositoryImpl.AccountRepositoryImpl
import com.aliad.repositoryImpl.CategoryRepositoryImpl
import com.aliad.repositoryImpl.DashBoardRepositoryImpl
import com.aliad.repositoryImpl.StoryTypeImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import kotlin.math.sin

val dataModule = module {

    // implement ktor clint
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                    prettyPrint = true
                })
            }
            install(Logging){
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(DefaultRequest){
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
    // implement ktor clint

    single<AccountRepository> {
        AccountRepositoryImpl(httpClient = get())
    }

    single {
        RemoteDataSources(httpClient = get())
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(dataSources = get())
    }

    single<DashBordRepository> {
        DashBoardRepositoryImpl(remoteDataSources = get())
    }

    single<StoryType> {
        StoryTypeImpl(remoteDataSources = get())
    }

}