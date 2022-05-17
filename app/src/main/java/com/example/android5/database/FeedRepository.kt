package com.example.android5.database

import com.example.android5.model.Noti

class FeedRepository( val feedDatabase: FeedDatabase) {

    suspend fun readalldata(): List<Feed>{
        return feedDatabase.FeedDao().readalldata()
    }
    suspend fun readnotifydata(): List<Noti>{
        return feedDatabase.FeedDao().readnotifydata()
    }

    suspend fun addFeed(feed: Feed){
        feedDatabase.FeedDao().addFeed(feed)
    }

}

