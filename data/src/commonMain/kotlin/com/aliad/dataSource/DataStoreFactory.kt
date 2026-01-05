package com.aliad.dataSource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

// add path to datastore
fun createDataStore(producerPath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { producerPath().toPath() }
    )
}

const val dataStoreFileName = "muktokowlom_dataStore.preferences_pb"