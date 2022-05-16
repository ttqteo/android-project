package com.example.android5.database

import android.app.Application
import com.example.android5.database.FeedDatabase
import com.example.android5.database.FeedRepository

class FeedData : Application() {
    lateinit var database : FeedDatabase
    val feedRepository by lazy { FeedRepository(database) }

    override fun onCreate() {
        super.onCreate()
        database = FeedDatabase.getDatabase(this)
    }
}