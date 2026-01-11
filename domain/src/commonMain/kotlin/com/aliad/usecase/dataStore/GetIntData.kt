package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetIntData constructor(private val dataStoreRepository: DataStoreRepository) {

    fun getIntData(key : String) : Flow<Int> = dataStoreRepository.getIntData(key = key)
}