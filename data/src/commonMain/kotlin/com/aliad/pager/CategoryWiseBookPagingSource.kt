package com.aliad.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aliad.dataSource.RemoteDataSources
import com.aliad.log.appLogger
import com.aliad.model.BookItem
import com.aliad.model.MyBookItem

class CategoryWiseBookPagingSource constructor(val remoteDataSources: RemoteDataSources) : PagingSource<Int, BookItem>() {

    init {
        appLogger("CategoryWiseBookPagingSource", "init")
    }

    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        appLogger("CategoryWiseBookPagingSource", "getRefreshKey")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        appLogger("CategoryWiseBookPagingSource", "load")

        return try {
            val page = params.key?: 1
            val response = remoteDataSources.getCategoryWiseBook(
                categoryID = 29,
                searchBy = "All",
                page = page
            )
            LoadResult.Page(
                data = response.data?.data?: emptyList(),
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(response.data?.last_page == page) null else page + 1
            )
        }catch (e : Exception){
            appLogger("CategoryWiseBookPagingSource", "exception ${e.message}")
            print("error paging 3 ${e.message}")
            LoadResult.Error(e)
        }
    }
}