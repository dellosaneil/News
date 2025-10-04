package com.thelazybattley.core.network.usecase

import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.enums.NetworkPath

interface FetchNewsUseCase {

    suspend operator fun invoke(keyword: String?, path: NetworkPath, pageSize: Int): Result<News>
}
