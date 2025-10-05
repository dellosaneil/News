package com.thelazybattley.core.db.usecase.impl

import com.thelazybattley.core.db.usecase.InsertNewsSourceDetailsUseCase
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.repository.NewsRepository
import javax.inject.Inject

class InsertNewsSourceDetailsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : InsertNewsSourceDetailsUseCase {
    override suspend fun invoke(sources: List<NewsSourceDetails>) =
        repository.insertNewsSources(sources)

}
