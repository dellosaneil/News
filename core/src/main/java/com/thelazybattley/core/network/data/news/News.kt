package com.thelazybattley.core.network.data.news

data class News(
    val articles: List<NewsArticle>,
    val status: String,
    val totalResults: Int
)

