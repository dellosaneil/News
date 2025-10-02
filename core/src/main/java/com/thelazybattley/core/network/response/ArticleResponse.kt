package com.thelazybattley.core.network.response

data class ArticleResponse(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceResponse,
    val title: String,
    val url: String,
    val urlToImage: String
)
