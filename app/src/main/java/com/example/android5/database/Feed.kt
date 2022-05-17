package com.example.android5.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="feed")
data class Feed(
    @PrimaryKey(autoGenerate = true)
    val uid: Int=0,
    val name: String,
    val hour: String,
    val status: String,
    val title_status: String?,
    val link_home_web: String?,
)


