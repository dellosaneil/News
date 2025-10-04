package com.thelazybattley.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thelazybattley.core.db.entity.NewsSourceDetailsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsSources(sources: List<NewsSourceDetailsEntity>)

    @Query("SELECT * FROM NewsSourceDetailsEntity")
    suspend fun getNewsSources(): List<NewsSourceDetailsEntity>


}
