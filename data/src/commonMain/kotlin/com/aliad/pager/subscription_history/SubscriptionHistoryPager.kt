package com.aliad.pager.subscription_history

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.subscription_history.Payment

class SubscriptionHistoryPager constructor(val remoteDataSources: RemoteDataSources) : PagingSource<Int, Payment>() {
    override fun getRefreshKey(state: PagingState<Int, Payment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Payment> {
        return try {
            val page = params.key?: 1
            val response = remoteDataSources.getSubscriptionHistory(
                page = page
            )
            LoadResult.Page(
                data = response.data?.payments?: emptyList(),
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(response.data?.payments?.isEmpty() == true) null else page + 1
            )
        }catch (e : Exception){
            print("error paging 3 ${e.message}")
            LoadResult.Error(e)
        }
    }
}