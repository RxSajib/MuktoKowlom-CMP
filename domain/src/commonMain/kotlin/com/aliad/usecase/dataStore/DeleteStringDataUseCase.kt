package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class DeleteStringDataUseCase constructor(private val dataStoreRepository: DataStoreRepository) {

    suspend fun removeStringData(key : String){
        dataStoreRepository.deleteStringData(key = key)
    }
}