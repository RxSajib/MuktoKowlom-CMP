package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class DeleteIntDataUseCase constructor(private val dataStoreRepository: DataStoreRepository) {

    suspend fun deleteIntData(key : String){
        dataStoreRepository.deleteIntData(key = key)
    }
}