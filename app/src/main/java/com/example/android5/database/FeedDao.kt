package com.example.android5.database

import androidx.room.*
import com.example.android5.model.Noti

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFeed( feed: Feed)


    @Query("SELECT * FROM feed ORDER BY uid DESC")
    suspend fun readalldata(): List<Feed>

    @Query("SELECT name, hour,title_status  FROM feed ORDER BY uid DESC")
    suspend fun readnotifydata(): List<Noti>

    @Delete
    suspend fun delete(feed: Feed)
}

