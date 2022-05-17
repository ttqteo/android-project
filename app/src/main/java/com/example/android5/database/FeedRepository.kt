package com.example.android5.database

import com.example.android5.model.Noti

class FeedRepository( val feedDatabase: FeedDatabase) {

    suspend fun readalldata(): List<Feed>{
        return feedDatabase.FeedDao().readalldata()
    }
    suspend fun readnotifydata(): List<Noti>{
        return feedDatabase.FeedDao().readnotifydata()
    }


    suspend fun addFeed(
    name: String,
    hour: String,
    status: String,
    title_status: String,
    link_home_web: String,

    ) {
        return feedDatabase.FeedDao().addFeed(
             Feed(
            name=name,
            hour=hour,
            status=status,
            title_status=title_status,
            link_home_web=link_home_web
                )
        )
    }

}

