package com.thelazybattley.feature.seeall.state

import androidx.paging.PagingData
import com.thelazybattley.core.network.data.news.NewsArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SeeAllViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val newsArticles : Flow<PagingData<NewsArticle>> = flowOf(PagingData.empty()),
    val isEndOfLists: Boolean = false,
)
