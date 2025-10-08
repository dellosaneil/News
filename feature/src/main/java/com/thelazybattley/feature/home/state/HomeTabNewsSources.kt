package com.thelazybattley.feature.home.state

import com.thelazybattley.core.network.data.sources.NewsSourceDetails

data class HomeTabNewsSources(
    val sources: List<NewsSourceDetails> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
