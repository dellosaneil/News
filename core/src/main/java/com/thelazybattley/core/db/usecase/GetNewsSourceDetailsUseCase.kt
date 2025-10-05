package com.thelazybattley.core.db.usecase

import com.thelazybattley.core.network.data.sources.NewsSourceDetails

interface GetNewsSourceDetailsUseCase {

    suspend operator fun invoke() : Result<List<NewsSourceDetails>>

}
