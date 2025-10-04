package com.thelazybattley.core.di.network

import com.thelazybattley.core.network.NewsRepository
import com.thelazybattley.core.network.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepositoryImpl(impl: NewsRepositoryImpl): NewsRepository
}
