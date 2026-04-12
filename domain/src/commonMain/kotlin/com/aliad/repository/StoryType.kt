package com.aliad.repository

import androidx.paging.PagingData
import com.aliad.ApiResult
import com.aliad.model.MyBookItem
import kotlinx.coroutines.flow.Flow

interface StoryType {
    fun getStoryType(searchKey: String, storyType : String): Flow<PagingData<MyBookItem>>

    fun getSearchStory(searchKey : String) : Flow<PagingData<MyBookItem>>

    fun getAllReleaseStory(searchKey : String) : Flow<PagingData<MyBookItem>>

    suspend fun getStoryDetails(storyID : String) : ApiResult<MyBookItem>
}