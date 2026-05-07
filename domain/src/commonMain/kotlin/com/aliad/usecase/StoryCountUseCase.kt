package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.StoryCount
import com.aliad.repository.AccountRepository

class StoryCountUseCase constructor(val accountRepository: AccountRepository) {

    suspend fun getStoryCount(userID : String) : ApiResult<StoryCount>{
        return accountRepository.storyCount(userID = userID)
    }
}