package com.thelazybattley.core.network.usecase.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.usecase.FetchNewsByKeywordUseCase
import javax.inject.Inject

class FetchNewsByKeywordUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : FetchNewsByKeywordUseCase {
    override suspend operator fun invoke(keyword: String) =
        repository.fetchNewsByKeyword(keyword = keyword)
}
