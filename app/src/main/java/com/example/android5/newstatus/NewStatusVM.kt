package com.example.android5.newstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.database.Feed
import com.example.android5.database.FeedRepository
import kotlinx.coroutines.launch

class NewStatusVM(private val feedRepository: FeedRepository) : ViewModel() {

    fun addFeed(name: String,
                hour: String,
                status: String,
                title_status: String,
                link_home_web: String,) {
        viewModelScope.launch {
            feedRepository.addFeed(name,hour,status,title_status,link_home_web)
        }
    }

}