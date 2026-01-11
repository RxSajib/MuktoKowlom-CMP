package com.aliad.usecase.dataStore

import com.aliad.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetStringData constructor(private val dataStoreRepository: DataStoreRepository) {

    fun getStringData(key : String) : Flow<String> = dataStoreRepository.getStringData(key = key)
}