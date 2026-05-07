package com.aliad.pager

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.BookItem
import com.aliad.utils.MyCustomLogger

private const val TAG = "PendingStoryPagingSourc"
class PendingStoryPagingSource constructor(val remoteDataSources: RemoteDataSources, val userID : String) : PagingSource<Int, BookItem>() {
    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        return try {
            MyCustomLogger.logInfo(tag = TAG, message = "success load")
            val page = params.key?: 1
            val response = remoteDataSources.pendingStoryList(
                page = page,
                userID = userID
            )
            Page(
                data = response.pendingStoriesList,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(response.last_page == page) null else page + 1
            )
        }catch (e : Exception){
            MyCustomLogger.logInfo(tag = TAG, message = "error ${e.message}")
            LoadResult.Error(e)
        }
    }
}