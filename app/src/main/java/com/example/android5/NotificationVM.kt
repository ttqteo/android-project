package com.example.android5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5.model.Noti

class NotificationVM : ViewModel() {
    private var _listOfData: MutableLiveData<List<Noti>> = MutableLiveData()
    val listOfData: LiveData<List<Noti>>
        get() = _listOfData

    fun loadData() {
        val data = DataStoreNoti.getDataSet()
        _listOfData.postValue(data)
    }
}