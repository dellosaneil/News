package com.thelazybattley.core.network.usecase

import com.thelazybattley.core.network.data.news.News

interface FetchNewsByKeyword {

    suspend fun fetchNewsByKeyword(keyword: String) : Result<News>
}
