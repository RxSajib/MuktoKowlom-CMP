package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class SaveBoolData constructor(private val dataStoreRepository: DataStoreRepository) {

    suspend fun saveBoolData(key : String, value : Boolean){
        dataStoreRepository.saveBoolData(key = key, value = value)
    }
}