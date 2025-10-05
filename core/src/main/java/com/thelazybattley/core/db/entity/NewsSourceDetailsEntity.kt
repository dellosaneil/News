package com.thelazybattley.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thelazybattley.core.network.data.sources.NewsSourceDetails

@Entity(tableName = "NewsSourceDetailsEntity")
data class NewsSourceDetailsEntity(
    val category: String,
    val country: String,
    val description: String,
    @PrimaryKey val id: String,
    val language: String,
    val name: String,
    val url: String,
    val imageUrl: String
)

fun NewsSourceDetailsEntity.toDomain(): NewsSourceDetails {
    return NewsSourceDetails(
        category = category,
        country = category,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url,
        imageUrl = imageUrl
    )
}
