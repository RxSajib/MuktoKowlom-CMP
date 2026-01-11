package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class SaveStringData constructor(val dataStoreRepository: DataStoreRepository) {

    suspend fun saveStringData(key : String, value : String){
        dataStoreRepository.saveStringData(key = key, value = value)
    }
}