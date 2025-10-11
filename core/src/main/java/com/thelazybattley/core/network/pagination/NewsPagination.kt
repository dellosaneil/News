package com.thelazybattley.core.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.data.news.NewsArticle

class NewsPagination(
    private val callback: suspend (Int) -> News
) : PagingSource<Int, NewsArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticle> {
        return try {
            val nextPage = params.key ?: 1
            val response = callback(nextPage)
            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = nextPage.inc()
            )
        } catch (exception: Exception) {
            LoadResult.Error(throwable = exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
