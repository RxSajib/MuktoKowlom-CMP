package com.aliad.usecase

import androidx.paging.PagingData
import com.aliad.model.MyBookItem
import com.aliad.repository.StoryType
import kotlinx.coroutines.flow.Flow

class StoryTypeUseCase constructor(val storyType: StoryType) {

    fun getStoryType(searchKey : String, searchType : String) : Flow<PagingData<MyBookItem>>{
      return  storyType.getStoryType(searchKey = searchKey, storyType = searchType)
    }
}