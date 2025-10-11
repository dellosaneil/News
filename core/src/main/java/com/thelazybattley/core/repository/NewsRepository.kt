package com.thelazybattley.core.repository

import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.util.LatestNewsCategories

interface NewsRepository {

    suspend fun fetchNews(
        keyword: String?, path: NetworkPath, pageSize: Int,
        category: LatestNewsCategories?,
        page: Int
    ): Result<News>

    suspend fun fetchNewsSources(): Result<List<NewsSourceDetails>>

    suspend fun getNewsSourceDetails(): Result<List<NewsSourceDetails>>

    suspend fun insertNewsSources(sources: List<NewsSourceDetails>)

}
