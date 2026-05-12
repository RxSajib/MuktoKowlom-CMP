package com.aliad.presentation.signIn.ui.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.User
import com.aliad.usecase.dataStore.DeleteBoolDataUseCase
import com.aliad.usecase.dataStore.DeleteIntDataUseCase
import com.aliad.usecase.dataStore.DeleteStringDataUseCase
import com.aliad.usecase.dataStore.GetBoolData
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.aliad.usecase.dataStore.SaveBoolData
import com.aliad.usecase.dataStore.SaveIntData
import com.aliad.usecase.dataStore.SaveStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DataStoreViewModel constructor(
    private val saveStringData: SaveStringData,
    private val getStringData: GetStringData,
    private val saveIntData: SaveIntData,
    private val getIntData: GetIntData,
    private val saveBoolData: SaveBoolData,
    private val getBoolData: GetBoolData,
    private val deleteStringDataUseCase: DeleteStringDataUseCase,
    private val deleteIntDataUseCase: DeleteIntDataUseCase,
    private val deleteBoolDataUseCase: DeleteBoolDataUseCase
) : ViewModel() {

    fun saveBoolData(key: String, value: Boolean) {
        viewModelScope.launch {
            saveBoolData.saveBoolData(key = key, value = value)
        }
    }

    fun saveIntData(key: String, value: Int) {
        viewModelScope.launch {
            saveIntData.saveIntData(key = key, value = value)
        }
    }

    fun saveStringData(key: String, value: String) {
        viewModelScope.launch {
            saveStringData.saveStringData(key = key, value = value)
        }
    }

    fun getBoolData(key: String): Flow<Boolean> = getBoolData.getBoolData(key = key)

    fun getIntData(key: String): Flow<Int> = getIntData.getIntData(key = key)

    fun getStringData(key: String): Flow<String> = getStringData.getStringData(key = key)


    fun deleteStringData(key: String) {
        viewModelScope.launch {
            deleteStringDataUseCase.removeStringData(key = key)
        }
    }

    fun deleteIntData(key: String) {
        viewModelScope.launch {
            deleteIntDataUseCase.deleteIntData(key = key)
        }
    }

    fun deleteBoolData(key: String) {
        viewModelScope.launch {
            deleteBoolDataUseCase.deleteBoolData(key = key)
        }
    }


    fun deleteUser() {
        viewModelScope.launch {
            supervisorScope {

                val jobs = listOf(
                    async {
                        deleteStringData(key = AppConstant.USER_NAME)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_PHONE)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_REGISTER_DATE)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_PROFILE_IMAGE)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_EMAIL_ADDRESS)
                    },
                    async {
                        deleteStringData(key = AppConstant.ACCESS_TOKEN)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_SECOND_NUMBER)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_DATE_OF_BIRTH)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_AGE)
                    },
                    async {
                        deleteStringData(key = AppConstant.USER_ADDRESS)
                    }
                )
                jobs.joinAll()
            }
        }

    }

}