package com.thelazybattley.feature.home.state

import com.thelazybattley.core.network.data.news.NewsArticle

data class HomeTabArticlesState(
    val articles: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
