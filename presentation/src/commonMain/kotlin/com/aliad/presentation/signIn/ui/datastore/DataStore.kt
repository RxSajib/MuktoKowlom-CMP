package com.aliad.presentation.signIn.ui.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.usecase.dataStore.GetBoolData
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.aliad.usecase.dataStore.SaveBoolData
import com.aliad.usecase.dataStore.SaveIntData
import com.aliad.usecase.dataStore.SaveStringData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataStore constructor(
    private val saveStringData: SaveStringData,
    private val getStringData: GetStringData,
    private val saveIntData: SaveIntData,
    private val getIntData: GetIntData,
    private val saveBoolData: SaveBoolData,
    private val getBoolData: GetBoolData
) : ViewModel() {


    fun saveBoolData(key: String, value: Boolean) {
        viewModelScope.launch {
            saveBoolData.saveBoolData(key = key, value = value)
        }
    }

    fun saveIntData(key: String, value: Int) {
        viewModelScope.launch {
            saveIntData.saveIntData(key = key, value = value)
        }
    }

    fun saveStringData(key: String, value: String) {
        viewModelScope.launch {
            saveStringData.saveStringData(key = key, value = value)
        }
    }

    fun getBoolData(key: String): Flow<Boolean> = getBoolData.getBoolData(key = key)

    fun getIntData(key: String): Flow<Int> = getIntData.getIntData(key = key)

    fun getStringData(key: String): Flow<String> = getStringData.getStringData(key = key)
}