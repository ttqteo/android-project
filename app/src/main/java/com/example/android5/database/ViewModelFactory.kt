package com.example.android5.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android5.HomeVM

class ViewModelFactory(private val feedData: FeedData) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedVM::class.java)) {
                val repo = feedData.feedRepository
                return FeedVM(repo) as T
            }
            throw IllegalArgumentException("unknown view model")
        }

}