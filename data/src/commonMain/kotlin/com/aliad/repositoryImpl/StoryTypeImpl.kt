package com.aliad.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.MyBookItem
import com.aliad.model.mapper.DataMapper.toBookModel
import com.aliad.pager.AllStoryPagingSource
import com.aliad.pager.SearchStoryPagingSource
import com.aliad.pager.StoryTypePagingSource
import com.aliad.repository.StoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryTypeImpl constructor(val remoteDataSources: RemoteDataSources) : StoryType {
    override fun getStoryType(
        searchKey: String,
        storyType: String
    ): Flow<PagingData<MyBookItem>> {
        return Pager(
            config =  PagingConfig(pageSize = 20, enablePlaceholders = false) ,
            pagingSourceFactory = { StoryTypePagingSource(remoteDataSources = remoteDataSources, searchKey = searchKey, storyType = storyType)},
        ).flow.map {pagingData ->
            pagingData.map { bookItem ->
                toBookModel(bookItem = bookItem)
            }
        }
    }

    override fun getSearchStory(searchKey: String): Flow<PagingData<MyBookItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchStoryPagingSource(searchKey = searchKey, remoteDataSources = remoteDataSources)}
        ).flow.map {pagingData ->
            pagingData.map { bookItem ->
                toBookModel(bookItem = bookItem)
            }
        }
    }

    override fun getAllReleaseStory(searchKey: String): Flow<PagingData<MyBookItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AllStoryPagingSource(remoteDataSources = remoteDataSources, searchKey = searchKey)}
        ).flow.map {pagingData ->
            pagingData.map { bookItem ->
                toBookModel(bookItem = bookItem)
            }
        }
    }
}