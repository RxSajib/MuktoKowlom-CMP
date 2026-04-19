package com.aliad.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.aliad.dataSource.CacheDataStore
import com.aliad.dataSource.RemoteDataSources
import com.aliad.dataSource.Token
import com.aliad.dataSource.TokenManager
import com.aliad.pager.CategoryWiseBookPagingSource
import com.aliad.repository.AccountRepository
import com.aliad.repository.CategoryRepository
import com.aliad.repository.DashBordRepository
import com.aliad.repository.DataStoreRepository
import com.aliad.repository.ProfileRepository
import com.aliad.repository.SearchRepository
import com.aliad.repository.StoryType
import com.aliad.repository.SubscriptionHistoryRepository
import com.aliad.repositoryImpl.AccountRepositoryImpl
import com.aliad.repositoryImpl.CategoryRepositoryImpl
import com.aliad.repositoryImpl.DashBoardRepositoryImpl
import com.aliad.repositoryImpl.DataStoreRepositoryImpl
import com.aliad.repositoryImpl.ProfileRepositoryImpl
import com.aliad.repositoryImpl.SearchRepositoryImpl
import com.aliad.repositoryImpl.StoryTypeImpl
import com.aliad.repositoryImpl.SubscriptionHistoryRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
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
import kotlinx.coroutines.runBlocking
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

            install(Auth){

                this.bearer {
                    loadTokens {
                        val token : TokenManager = getKoin().get()
                        token.init()
                        BearerTokens(
                            accessToken = token.getToken(),
                            refreshToken = ""
                        )
                    }
                }
            }
        }
    }
    // implement ktor clint

    single<AccountRepository> {
        AccountRepositoryImpl(remoteDataSources = get())
    }

    single {
        RemoteDataSources(httpClient = get())
    }

    // dataStore create
    single {
        CacheDataStore(datstore = get<DataStore<Preferences>>())
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

    single<DataStoreRepository> {
        DataStoreRepositoryImpl(datastore = get())
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(dataSources = get())
    }

    single<SearchRepository> {
        SearchRepositoryImpl(remoteDataSources = get())
    }

    single<SubscriptionHistoryRepository> {
        SubscriptionHistoryRepositoryImpl(remoteDataSources = get())
    }

    single {
        TokenManager(
            repository = get()
        )
    }


}