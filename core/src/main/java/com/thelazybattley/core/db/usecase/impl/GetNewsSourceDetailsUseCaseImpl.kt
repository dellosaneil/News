package com.thelazybattley.core.db.usecase.impl

import com.thelazybattley.core.db.usecase.GetNewsSourceDetailsUseCase
import com.thelazybattley.core.repository.NewsRepository
import javax.inject.Inject

class GetNewsSourceDetailsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetNewsSourceDetailsUseCase {
    override suspend fun invoke() = repository.fetchNewsSources()
}
