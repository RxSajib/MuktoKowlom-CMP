package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository

class DeleteBoolDataUseCase constructor(private val dataStoreRepository: DataStoreRepository) {

    suspend fun deleteBoolData(key : String){
        dataStoreRepository.deleteBoolData(key = key)
    }
}