package com.thelazybattley.core.network.usecase.impl

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.usecase.FetchNewsByKeyword
import javax.inject.Inject

class FetchNewsByKeywordImpl @Inject constructor(
    private val repository: NewsRepository
) : FetchNewsByKeyword {
    override suspend fun fetchNewsByKeyword(keyword: String) =
        repository.fetchNewsByKeyword(keyword = keyword)
}
