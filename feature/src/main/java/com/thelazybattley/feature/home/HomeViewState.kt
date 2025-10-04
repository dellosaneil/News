package com.thelazybattley.feature.home

import com.thelazybattley.core.network.data.news.News

data class HomeViewState(
    val trendingNews: News? = null
)
