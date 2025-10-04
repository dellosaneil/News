package com.thelazybattley.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
