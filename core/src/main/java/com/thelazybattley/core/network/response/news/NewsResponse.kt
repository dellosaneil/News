package com.thelazybattley.core.network.response.news

import com.thelazybattley.core.network.data.news.News

data class NewsResponse(
    val articles: List<NewsArticleResponse>,
    val status: String,
    val totalResults: Int
)

fun NewsResponse.toDomain() : News {
    return News(
        articles = articles.map { it.toDomain() },
        status = status,
        totalResults = totalResults
    )
}
