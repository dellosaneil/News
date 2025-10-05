package com.thelazybattley.core.network.data.sources

import com.thelazybattley.core.db.entity.NewsSourceDetailsEntity

data class NewsSourceDetails(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String,
    val imageUrl: String
)

fun NewsSourceDetails.toEntity(): NewsSourceDetailsEntity {
    return NewsSourceDetailsEntity(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url,
        imageUrl = imageUrl
    )

}
