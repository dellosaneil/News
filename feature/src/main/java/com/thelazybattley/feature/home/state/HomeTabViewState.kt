package com.thelazybattley.feature.home.state

import com.thelazybattley.core.util.LatestNewsCategories

data class HomeTabViewState(
    val trendingArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val newsSources: HomeTabNewsSources = HomeTabNewsSources(),
    val businessArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val entertainmentArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val generalArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val healthArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val scienceArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val sportsArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val technologyArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val highlightedArticles: HomeTabArticlesState = HomeTabArticlesState(),
    val keyword: String? = null,
    val categoryTabSelected: LatestNewsCategories = LatestNewsCategories.BUSINESS
)
