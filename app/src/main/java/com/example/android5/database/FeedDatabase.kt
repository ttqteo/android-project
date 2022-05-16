package com.example.android5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Feed::class], version = 1, exportSchema = false)
abstract class FeedDatabase: RoomDatabase() {
    abstract fun FeedDao(): FeedDao
    companion object {
        @Volatile
        private var INSTANCE: FeedDatabase? = null

//        fun getDatabase(context: Context): FeedDatabase {
//            val temInstance=INSTANCE
//            if (temInstance!=null)
//                return temInstance
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FeedDatabase::class.java, "feed_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
        fun getDatabase(ctx: Context): FeedDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    FeedDatabase::class.java, "feed_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
