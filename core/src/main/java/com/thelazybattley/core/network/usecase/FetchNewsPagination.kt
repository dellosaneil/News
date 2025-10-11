package com.thelazybattley.core.network.usecase

import androidx.paging.PagingData
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.util.LatestNewsCategories
import kotlinx.coroutines.flow.Flow

interface FetchNewsPagination {

    suspend operator fun invoke(
        keyword: String?, path: NetworkPath, pageSize: Int,
        category: LatestNewsCategories?
    ): Flow<PagingData<NewsArticle>>

}
