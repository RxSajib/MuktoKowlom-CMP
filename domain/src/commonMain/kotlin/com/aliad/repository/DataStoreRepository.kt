package com.aliad.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository{

    suspend fun saveStringData(key : String, value : String)
    fun getStringData(key : String) : Flow<String>

    suspend fun saveIntData(key : String, value : Int)
    fun getIntData(key : String) : Flow<Int>

    suspend fun saveBoolData(key : String, value : Boolean)
    fun getBoolData(key : String) : Flow<Boolean>
}