package com.thelazybattley.core.di.db

import android.content.Context
import androidx.room.Room

import com.thelazybattley.core.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder<NewsDatabase>(
            context,
            "news_database"
        ).build()
    }

    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()

}
