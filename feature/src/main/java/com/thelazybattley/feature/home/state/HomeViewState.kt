package com.thelazybattley.feature.home.state

data class HomeViewState(
    val trendingArticles: HomeArticlesState = HomeArticlesState(),
    val newsSources: HomeNewsSources = HomeNewsSources(),
    val businessArticles: HomeArticlesState = HomeArticlesState(),
    val entertainmentArticles: HomeArticlesState = HomeArticlesState(),
    val generalArticles: HomeArticlesState = HomeArticlesState(),
    val healthArticles: HomeArticlesState = HomeArticlesState(),
    val scienceArticles: HomeArticlesState = HomeArticlesState(),
    val sportsArticles: HomeArticlesState = HomeArticlesState(),
    val technologyArticles: HomeArticlesState = HomeArticlesState(),
    val highlightedArticles: HomeArticlesState = HomeArticlesState()
)
