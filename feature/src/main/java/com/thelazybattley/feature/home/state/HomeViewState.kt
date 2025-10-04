package com.thelazybattley.feature.home.state

data class HomeViewState(
    val trendingArticles: HomeTrendingNewsState = HomeTrendingNewsState(),
    val newsSources: HomeNewsSources = HomeNewsSources()
)
