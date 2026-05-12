package com.aliad.presentation.signIn.ui.profile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aliad.ApiResult
import com.aliad.model.ApiException
import com.aliad.model.ApiResponse
import com.aliad.model.GenericResponse
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.usecase.DeleteAccountUseCase
import com.aliad.usecase.StoryCountUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.aliad.usecase.dataStore.SaveIntData
import com.aliad.usecase.dataStore.SaveStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.toString

private const val TAG = "ProfileViewModel"
class ProfileViewModel constructor(val deleteAccountUseCase: DeleteAccountUseCase,
    val getStringData: GetStringData,
    val getIntData: GetIntData,
    val storyCountUseCase: StoryCountUseCase,
    val saveIntData: SaveIntData,
    val saveStringData: SaveStringData
    ) : ViewModel() {

        init {
            getStoryCount()
        }


    var logoutDialogShow by mutableStateOf(false)
    var deleteAccountDialogShow by mutableStateOf(false)

    var data = MutableSharedFlow<ApiResponse>()

    var isLoading by mutableStateOf(false)

    fun deleteAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading  =true
            val response = deleteAccountUseCase.deleteAccount()
            isLoading  =false
            when(response){
                is ApiResult.Success -> {

                    print("success ${response.data.message_en}")
                    data.emit(ApiResponse(
                        success = true,
                        message_en = response.data.message_en,
                        message_bn = response.data.message_bn
                    ))
                }
                is ApiResult.Error -> {
                    print("failed ${response.messageEn}")
                    data.emit(ApiResponse(
                        success = false,
                        message_en = response.messageEn,
                        message_bn = response.messageBn
                    ))
                }
            }
        }
    }

    val userName = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_NAME).first())
    }
    val userEmailAddress = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_EMAIL_ADDRESS).first())
    }
    val userProfileImage = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_PROFILE_IMAGE).first())
    }
    val userPhone = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_PHONE).first())
    }
    val userRegisterData = flow {
        emit(getStringData.getStringData(key = AppConstant.USER_REGISTER_DATE).first())
    }
    val accessToken = flow {
        emit(getStringData.getStringData(key = AppConstant.ACCESS_TOKEN).first())
    }

    val liveStoryCount = flow {
        emit(getIntData.getIntData(key = AppConstant.ACTIVE_STORY_COUNT).first())
    }
    val pendingStoryCount = flow {
        emit(getIntData.getIntData(key = AppConstant.PENDING_STORY_COUNT).first())
    }
    val joinSince = flow {
        emit(getStringData.getStringData(key = AppConstant.JOIN_SINCE).first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getStoryCount(){
        viewModelScope.launch {

            try {
                getIntData.getIntData(key = AppConstant.USER_ID).flatMapLatest { id ->
                    flow {
                        emit(storyCountUseCase.getStoryCount(userID = id.toString()))}
                }.flowOn(Dispatchers.IO).collectLatest {apiResult ->
                    when(apiResult){
                        is ApiResult.Success -> {
                            saveIntData.saveIntData(key = AppConstant.ACTIVE_STORY_COUNT, apiResult.data.liveStoryCount)
                            saveIntData.saveIntData(key = AppConstant.PENDING_STORY_COUNT, apiResult.data.pendingStoryCount)
                            saveStringData.saveStringData(key = AppConstant.JOIN_SINCE, apiResult.data.joinSince)
                        }
                        is ApiResult.Error -> {
                            MyCustomLogger.logInfo(tag = TAG, message = "error")
                        }
                    }
                }
            }catch (e : Exception){
                MyCustomLogger.logInfo(tag = TAG, message = e.message?: "")
            }

        }
    }


    // story count implementation

    // story count implementation
}

