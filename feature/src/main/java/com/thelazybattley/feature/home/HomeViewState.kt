package com.thelazybattley.feature.home

data class HomeViewState(
    val trendingArticles: HomeTrendingNewsState = HomeTrendingNewsState()
)
