package com.aliad.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.BookItem

class StoryTypePagingSource constructor(val remoteDataSources: RemoteDataSources, val storyType : String, val searchKey : String, ) : PagingSource<Int, BookItem>() {
    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        return try {
            val page = params.key?: 1
            val data = remoteDataSources.getStoryType(page = page, searchKey = searchKey, storyType = storyType)
            LoadResult.Page(
                data = data.data?.data?: emptyList(),
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(data.data?.last_page == page) null else page + 1
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}