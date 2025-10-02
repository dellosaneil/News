package com.thelazybattley.core.network.usecase

import com.thelazybattley.core.network.data.news.News

interface FetchNewsByKeywordUseCase {

    suspend operator fun invoke(keyword: String) : Result<News>
}
