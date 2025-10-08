package com.thelazybattley.core.network.data.news

import com.thelazybattley.core.util.TimeAgo

data class NewsArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsSource,
    val title: String,
    val url: String,
    val urlToImage: String,
    val timePassed: TimeAgo
)
