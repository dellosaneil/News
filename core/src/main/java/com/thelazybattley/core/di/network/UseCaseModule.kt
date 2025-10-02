package com.thelazybattley.core.di.network

import com.thelazybattley.core.network.usecase.FetchNewsByKeywordUseCase
import com.thelazybattley.core.network.usecase.impl.FetchNewsByKeywordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @ViewModelScoped
    @Binds
    abstract fun bindFetchNewsByKeyword(impl: FetchNewsByKeywordUseCaseImpl): FetchNewsByKeywordUseCase

}
