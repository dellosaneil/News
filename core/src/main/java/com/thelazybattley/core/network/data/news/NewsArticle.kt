package com.thelazybattley.core.network.data.news

data class NewsArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsSource,
    val title: String,
    val url: String,
    val urlToImage: String,
    val timePassed: String
)
