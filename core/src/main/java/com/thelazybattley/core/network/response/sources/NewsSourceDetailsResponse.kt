package com.thelazybattley.core.network.response.sources

import com.thelazybattley.core.network.data.sources.NewsSourceDetails

data class NewsSourceResponse(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
)

fun NewsSourceResponse.toDomain() : NewsSourceDetails {
    return NewsSourceDetails(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url,
        imageUrl = ""
    )
}
