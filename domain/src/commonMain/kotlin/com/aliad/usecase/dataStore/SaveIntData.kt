package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class SaveIntData constructor(private val dataStoreRepository: DataStoreRepository){

    suspend fun saveIntData(key : String, value : Int){
        dataStoreRepository.saveIntData(key = key, value = value)
    }
}