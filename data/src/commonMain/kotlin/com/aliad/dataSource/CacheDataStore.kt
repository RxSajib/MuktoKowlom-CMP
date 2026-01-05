package com.aliad.dataSource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CacheDataStore constructor(private val datstore: DataStore<Preferences>) {

   suspend fun saveAnyData(data: String){
        datstore.edit {
            it[stringPreferencesKey("key")] = data
        }
    }

   suspend fun getAnyData() : String? {
      val preferences =  datstore.data.first()
        return preferences[stringPreferencesKey("key")]
    }
}