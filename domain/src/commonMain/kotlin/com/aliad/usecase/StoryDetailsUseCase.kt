package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.MyBookItem
import com.aliad.repository.StoryType

class StoryDetailsUseCase constructor(val storyType: StoryType) {

    suspend fun getStoryDetails(storyID : String) : ApiResult<MyBookItem> = storyType.getStoryDetails(storyID = storyID)
}