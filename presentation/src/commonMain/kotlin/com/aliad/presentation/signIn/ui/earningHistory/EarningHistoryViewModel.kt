package com.aliad.presentation.signIn.ui.earningHistory

import androidx.lifecycle.ViewModel
import com.aliad.ApiResult
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.usecase.EarningHistoryUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlin.collections.emptyList

private const val TAG = "EarningHistoryViewModel"
class EarningHistoryViewModel constructor(
    val earningHistoryUseCase: EarningHistoryUseCase,
    val getIntData: GetIntData
) : ViewModel() {


    val userID = flow {
        emit(getIntData.getIntData(key = AppConstant.USER_ID).first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val getEarningHistory = userID.flatMapLatest { userID ->
        when (val response = earningHistoryUseCase.getEarningHistory(userID = userID.toString())) {
            is ApiResult.Success -> {
                MyCustomLogger.logInfo(tag = TAG, message = "success")
                flowOf(response.data)
            }

            is ApiResult.Error -> {
                MyCustomLogger.logInfo(tag = TAG, message = "failed $userID")
                flowOf(emptyList())
            }
        }
    }
}