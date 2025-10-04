package com.thelazybattley.core.network.usecase

import com.thelazybattley.core.network.data.sources.NewsSourceDetails

interface FetchNewsSourcesUseCase {
    suspend operator fun invoke(): Result<List<NewsSourceDetails>>
}
