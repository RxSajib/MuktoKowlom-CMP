package com.aliad.dataSource

import com.aliad.repository.DataStoreRepository
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class TokenManager(private val repository: DataStoreRepository) {

    private var token: String = ""

    suspend fun init() {
        token = repository.getStringData(AppConstant.ACCESS_TOKEN).first()
    }



    fun getToken(): String = token

    suspend fun updateToken(newToken: String) {
        token = newToken
        repository.saveStringData(AppConstant.ACCESS_TOKEN, newToken)
    }
}