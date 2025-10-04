package com.thelazybattley.core.network.usecase.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.usecase.FetchNewsSourcesUseCase
import javax.inject.Inject

class FetchNewsSourcesUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : FetchNewsSourcesUseCase {
    override suspend fun invoke(): Result<List<NewsSourceDetails>> {
        return try {
            val result = repository
                .fetchNewsSources()
                .getOrThrow()
                .map { source ->
                    source.copy(
                        imageUrl = "https://www.google.com/s2/favicons?domain=${source.url}"
                    )
                }
            Result.success(value = result)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
