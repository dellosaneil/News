package com.thelazybattley.core.di.network

import com.thelazybattley.core.network.usecase.FetchNewsByKeyword
import com.thelazybattley.core.network.usecase.impl.FetchNewsByKeywordImpl
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
    abstract fun bindFetchNewsByKeyword(impl: FetchNewsByKeywordImpl): FetchNewsByKeyword

}
