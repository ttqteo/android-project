package com.example.android5.notify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android5.database.FeedData

class ViewModelFactory(private val feedData: FeedData) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NotificationVM::class.java)) {
                val repo = feedData.feedRepository
                return NotificationVM(repo) as T
            }
            throw IllegalArgumentException("unknown view model")
        }

}