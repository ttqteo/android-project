package com.example.android5.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FeedVM(private val feedRepository: FeedRepository) : ViewModel() {

    private var _listOfData: MutableLiveData<List<Feed>> = MutableLiveData()
    val listOfData: LiveData<List<Feed>>
        get() = _listOfData

    fun addFeed(feed:Feed) {
        viewModelScope.launch {
            feedRepository.addFeed(feed)
        }
    }
    fun loadData() {
        viewModelScope.launch {
            val data = feedRepository.readalldata()
            _listOfData.postValue(data)
        }
    }
}