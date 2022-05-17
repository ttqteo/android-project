package com.example.android5.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.DataStoreFeed
import com.example.android5.database.FeedRepository
import kotlinx.coroutines.launch
import com.example.android5.database.Feed




class HomeVM(private val feedRepository: FeedRepository) : ViewModel() {

    private var _listOfData: MutableLiveData<List<Feed>> = MutableLiveData()
    val listOfData: LiveData<List<Feed>>
        get() = _listOfData


    fun loadData() {
        viewModelScope.launch {
            val data = feedRepository.readalldata()
            _listOfData.postValue(data)
        }
    }
}