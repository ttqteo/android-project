package com.example.android5.newstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android5.database.FeedData

class ViewModelFactory(private val feedData: FeedData) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewStatusVM::class.java)) {
                val repo = feedData.feedRepository
                return NewStatusVM(repo) as T
            }
            throw IllegalArgumentException("unknown view model")
        }

}