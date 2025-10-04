package com.thelazybattley.core.network.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.NewsService
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.response.news.toDomain
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {
    override suspend fun fetchNews(
        keyword: String?,
        path: NetworkPath,
        pageSize: Int
    ): Result<News> {
        return try {
            Result.success(
                newsService.fetchNews(
                    keyword = keyword,
                    path = path.path,
                    pageSize = pageSize
                ).toDomain()
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
