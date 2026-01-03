package com.aliad.repository

import androidx.paging.PagingData
import com.aliad.model.MyBookItem
import kotlinx.coroutines.flow.Flow

interface StoryType {
    fun getStoryType(searchKey: String, storyType : String): Flow<PagingData<MyBookItem>>
}