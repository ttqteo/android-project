package com.example.android5.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android5.database.FeedData

class ViewModelFactory(private val feedData: FeedData) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeVM::class.java)) {
                val repo = feedData.feedRepository
                return HomeVM(repo) as T
            }
            throw IllegalArgumentException("unknown view model")
        }

}