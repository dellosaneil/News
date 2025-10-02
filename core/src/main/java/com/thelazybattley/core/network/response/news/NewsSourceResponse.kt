package com.thelazybattley.core.network.response.news

import com.thelazybattley.core.network.data.news.NewsSource

data class NewsSourceResponse(
    val id: String,
    val name: String
)

fun NewsSourceResponse.toDomain(): NewsSource  {
    return NewsSource(id = id, name = name)
}
