package com.thelazybattley.core.network.usecase.impl

import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import com.thelazybattley.core.repository.NewsRepository
import com.thelazybattley.core.util.LatestNewsCategories
import com.thelazybattley.core.util.usecase.ComputeTimePassedUseCase
import javax.inject.Inject

class FetchNewsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
    private val computeTimePassedUseCase: ComputeTimePassedUseCase
) : FetchNewsUseCase {
    override suspend operator fun invoke(
        keyword: String?,
        path: NetworkPath,
        pageSize: Int,
        category: LatestNewsCategories?
    ): Result<News> {
        val result = repository.fetchNews(
            keyword = keyword, path = path, pageSize = pageSize,
            category = category,
        ).getOrElse(
            onFailure = { exception ->
                return Result.failure(exception = exception)
            }
        )
        return Result.success(
            value = result.copy(
                articles = result.articles.map { article ->
                    article.copy(
                        timePassed = computeTimePassedUseCase(article.publishedAt)
                    )
                }
            )
        )
    }
}
