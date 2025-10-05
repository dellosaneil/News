package com.thelazybattley.core.repository.impl

import com.thelazybattley.core.db.dao.NewsDao
import com.thelazybattley.core.db.entity.toDomain
import com.thelazybattley.core.network.NewsService
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.data.sources.toEntity
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.response.news.toDomain
import com.thelazybattley.core.network.response.sources.toDomain
import com.thelazybattley.core.repository.NewsRepository
import com.thelazybattley.core.util.LatestNewsCategories
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao
) : NewsRepository {
    override suspend fun fetchNews(
        keyword: String?,
        path: NetworkPath,
        pageSize: Int,
        category: LatestNewsCategories?
    ): Result<News> {
        return try {
            Result.success(
                value = newsService.fetchNews(
                    keyword = keyword,
                    path = path.path,
                    pageSize = pageSize,
                    category = category?.query,
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

    override suspend fun getNewsSourceDetails(): Result<List<NewsSourceDetails>> {
        return try {
            Result.success(
                value = newsDao
                    .getNewsSourcesDetails()
                    .map { sources ->
                        sources.toDomain()
                    }
            )
        } catch (exception: Exception) {
            Result.failure(exception = exception)
        }
    }

    override suspend fun insertNewsSources(sources: List<NewsSourceDetails>) {
        newsDao
            .insertNewsSources(
                sources = sources.map { sources ->
                    sources.toEntity()
                }
            )
    }
}
