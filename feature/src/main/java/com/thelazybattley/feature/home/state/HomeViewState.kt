package com.thelazybattley.feature.home.state

import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.util.LatestNewsCategories

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
    val categorySelected: LatestNewsCategories = LatestNewsCategories.BUSINESS,
    val highlightedArticles: List<NewsArticle> = emptyList()
)
