package com.thelazybattley.core.network.usecase.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.usecase.FetchNewsByKeywordUseCase
import com.thelazybattley.core.util.usecase.ComputeTimePassedUseCase
import javax.inject.Inject

class FetchNewsByKeywordUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
    private val computeTimePassedUseCase: ComputeTimePassedUseCase
) : FetchNewsByKeywordUseCase {
    override suspend operator fun invoke(keyword: String): Result<News> {
        val result = repository.fetchNewsByKeyword(keyword = keyword)
            .getOrElse(onFailure = { exception ->
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
