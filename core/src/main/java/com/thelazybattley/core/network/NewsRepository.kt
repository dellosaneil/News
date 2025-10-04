package com.thelazybattley.core.network

import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.enums.NetworkPath

interface NewsRepository {

    suspend fun fetchNews(keyword: String?, path: NetworkPath): Result<News>

}
