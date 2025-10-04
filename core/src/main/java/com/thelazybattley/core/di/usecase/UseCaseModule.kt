package com.thelazybattley.core.di.usecase

import com.thelazybattley.core.network.usecase.FetchNewsSourcesUseCase
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import com.thelazybattley.core.network.usecase.impl.FetchNewsSourcesUseCaseImpl
import com.thelazybattley.core.network.usecase.impl.FetchNewsUseCaseImpl
import com.thelazybattley.core.util.usecase.ComputeTimePassedUseCase
import com.thelazybattley.core.util.usecase.impl.ComputeTimePassedUseCaseImpl
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
    abstract fun bindFetchNews(impl: FetchNewsUseCaseImpl): FetchNewsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindComputeTimePassedUseCase(impl: ComputeTimePassedUseCaseImpl): ComputeTimePassedUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindFetchNewsSourcesUseCase(impl: FetchNewsSourcesUseCaseImpl): FetchNewsSourcesUseCase

}
