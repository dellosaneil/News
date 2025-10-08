package com.thelazybattley.core.network.response.news

import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.util.TimeAgo

data class NewsArticleResponse(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: NewsSourceResponse,
    val title: String,
    val url: String,
    val urlToImage: String?
)

fun NewsArticleResponse.toDomain(): NewsArticle {
    return NewsArticle(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt,
        source = source.toDomain(),
        title = title,
        url = url,
        urlToImage = urlToImage ?: "",
        timePassed = TimeAgo.Days(value = 0),
    )
}
