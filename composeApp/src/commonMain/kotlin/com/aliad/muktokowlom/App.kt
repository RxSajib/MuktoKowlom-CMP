package com.aliad.muktokowlom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliad.muktokowlom.ui.navigation.root.RootNavigation
import com.aliad.muktokowlom.ui.theme.MuktoKowlomTheme
import com.aliad.muktokowlom.utils.Localization
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.sajib.data.appConstant.AppConstant
import dev.shivathapaa.logger.api.LogLevel
import dev.shivathapaa.logger.api.LoggerFactory
import dev.shivathapaa.logger.core.LoggerConfig
import dev.shivathapaa.logger.sink.DefaultLogSink
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {

    val languages: Localization = koinInject()
    val dataStore: DataStoreViewModel = koinViewModel()
    val local =
        dataStore.getStringData(key = AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")

    MuktoKowlomTheme {

        languages.setLocal(local.value)
        RootNavigation()


        initLogger()
    }


    // global exception handler
    val exception = CoroutineExceptionHandler { _, throwable ->
        print("error with exception ${throwable.message}")

        // log the event
    }
    // global exception handler

}

fun initLogger() {
    val config = LoggerConfig.Builder()
        .minLevel(LogLevel.DEBUG)
        .addSink(DefaultLogSink())
        .build()

    LoggerFactory.install(config)
}