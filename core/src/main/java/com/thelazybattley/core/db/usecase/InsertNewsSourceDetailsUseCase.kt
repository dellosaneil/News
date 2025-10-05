package com.thelazybattley.core.db.usecase

import com.thelazybattley.core.network.data.sources.NewsSourceDetails

interface InsertNewsSourceDetailsUseCase {

    suspend operator fun invoke(sources: List<NewsSourceDetails>)


}
