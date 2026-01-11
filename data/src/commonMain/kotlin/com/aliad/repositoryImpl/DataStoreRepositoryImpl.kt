package com.aliad.repositoryImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.aliad.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl constructor(val datastore : DataStore<Preferences>) : DataStoreRepository {
    override suspend fun saveStringData(key: String, value: String) {
        datastore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override fun getStringData(key: String): Flow<String> {
      return  datastore.data.map {
            it[stringPreferencesKey(key)] ?: ""
        }
    }

    override suspend fun saveIntData(key: String, value: Int) {
        datastore.edit {
            it[intPreferencesKey(key)] = value
        }
    }

    override fun getIntData(key: String): Flow<Int> {
        return datastore.data.map {
            it[intPreferencesKey(key)] ?: 0
        }
    }

    override suspend fun saveBoolData(key: String, value: Boolean) {
        datastore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    override fun getBoolData(key: String): Flow<Boolean> {
        return datastore.data.map {
            it[booleanPreferencesKey(key)] ?: false
        }
    }
}