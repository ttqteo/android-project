package com.example.android5.database

import androidx.room.*
@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFeed( feed: Feed)


    @Query("SELECT * FROM feed")
    suspend fun readalldata(): List<Feed>

    @Delete
    suspend fun delete(feed: Feed)
}

