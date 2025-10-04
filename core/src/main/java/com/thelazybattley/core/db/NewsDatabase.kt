package com.thelazybattley.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thelazybattley.core.db.dao.NewsDao
import com.thelazybattley.core.db.entity.NewsSourceDetailsEntity

@Database(entities = [NewsSourceDetailsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
