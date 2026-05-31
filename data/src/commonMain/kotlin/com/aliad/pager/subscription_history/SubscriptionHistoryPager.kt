package com.aliad.pager.subscription_history

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.subscription_history.Payment
import com.aliad.utils.MyCustomLogger

private const val TAG = "SubscriptionHistoryPage"
private const val PAGE_CURRENT_KEY = 1
class SubscriptionHistoryPager constructor(val remoteDataSources: RemoteDataSources) : PagingSource<Int, Payment>() {
    override fun getRefreshKey(state: PagingState<Int, Payment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Payment> {
        return try {
            MyCustomLogger.logInfo(tag = TAG, message = "success")
            val page = params.key?: PAGE_CURRENT_KEY
            val response = remoteDataSources.getSubscriptionHistory(
                page = page
            )
            val payments = response.data?.payments ?: emptyList()
            val total = response.data?.total ?: 0
            val perPage = response.data?.per_page ?: 10

            val isLastPage = (page * perPage) >= total

            LoadResult.Page(
                data = payments,
                prevKey = if (page == PAGE_CURRENT_KEY) null else page - 1,
                nextKey = if (isLastPage || payments.isEmpty()) null else page + 1
            )
        }catch (e : Exception){
            MyCustomLogger.logInfo(tag = TAG, message = e.message?: "Something went wrong")
            LoadResult.Error(e)
        }
    }
}