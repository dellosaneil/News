package com.thelazybattley.core.network.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.NewsService
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.response.news.toDomain
import com.thelazybattley.core.network.response.sources.toDomain
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
                value = newsService.fetchNews(
                    keyword = keyword,
                    path = path.path,
                    pageSize = pageSize
                ).toDomain()
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun fetchNewsSources(): Result<List<NewsSourceDetails>> {
        return try {
            Result.success(
                value = newsService.fetchNewsSources()
                    .sources
                    .map { source -> source.toDomain() }
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
