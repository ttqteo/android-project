package com.example.android5.database

class FeedRepository( val feedDatabase: FeedDatabase) {

    suspend fun readalldata(): List<Feed>{
        return feedDatabase.FeedDao().readalldata()
    }
//    val readalldata: List<Feed> = feedDatabase.FeedDao().readalldata()

    suspend fun addFeed(feed: Feed){
        feedDatabase.FeedDao().addFeed(feed)
    }

}

