package com.thelazybattley.core.network.response.sources

data class NewsSourcesDetailsResponse(
    val sources: List<NewsSourceResponse>,
    val status: String
)
