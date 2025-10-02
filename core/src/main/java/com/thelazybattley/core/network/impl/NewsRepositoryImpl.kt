package com.thelazybattley.core.network.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.NewsService
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.response.news.toDomain
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {
    override suspend fun fetchNewsByKeyword(keyword: String): Result<News> {
        return try {
            Result.success(newsService.fetchNewsByKeyword(keyword = keyword).toDomain())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
