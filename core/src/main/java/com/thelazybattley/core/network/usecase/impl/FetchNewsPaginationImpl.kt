package com.thelazybattley.core.network.usecase.impl

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.pagination.NewsPagination
import com.thelazybattley.core.network.usecase.FetchNewsPagination
import com.thelazybattley.core.repository.NewsRepository
import com.thelazybattley.core.util.LatestNewsCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNewsPaginationImpl @Inject constructor(
    private val repository: NewsRepository
) : FetchNewsPagination {
    override suspend fun invoke(
        keyword: String?,
        path: NetworkPath,
        pageSize: Int,
        category: LatestNewsCategories?
    ): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagination(
                    callback = { page ->
                        repository.fetchNews(
                            page = page,
                            keyword = keyword,
                            pageSize = PAGE_SIZE,
                            category = category,
                            path = path
                        ).getOrThrow()
                    }
                )
            }
        ).flow
    }
}
