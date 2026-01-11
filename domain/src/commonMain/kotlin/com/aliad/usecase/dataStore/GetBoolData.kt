package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetBoolData constructor(private val dataStoreRepository: DataStoreRepository) {

    fun getBoolData(key : String) : Flow<Boolean> = dataStoreRepository.getBoolData(key = key)
}