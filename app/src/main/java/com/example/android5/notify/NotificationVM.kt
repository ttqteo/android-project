package com.example.android5.notify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.DataStoreNoti
import com.example.android5.database.FeedRepository
import com.example.android5.model.Noti
import kotlinx.coroutines.launch

class NotificationVM (private val feedRepository: FeedRepository) : ViewModel() {
    private var _listOfData: MutableLiveData<List<Noti>> = MutableLiveData()
    val listOfData: LiveData<List<Noti>>
        get() = _listOfData

    fun loadData() {
        viewModelScope.launch {
            val data = feedRepository.readnotifydata()
            _listOfData.postValue(data)
        }
    }
}