package com.thelazybattley.core.db.usecase.impl

import com.thelazybattley.core.db.usecase.GetNewsSourceDetailsUseCase
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.repository.NewsRepository
import javax.inject.Inject

class GetNewsSourceDetailsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetNewsSourceDetailsUseCase {
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
