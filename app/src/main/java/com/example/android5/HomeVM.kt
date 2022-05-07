package com.example.android5


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5.model.Feed


class HomeVM : ViewModel() {
    private var _listOfData: MutableLiveData<List<Feed>> = MutableLiveData()
    val listOfData: LiveData<List<Feed>>
        get() = _listOfData

    fun loadData() {
        val data = DataStoreFeed.getDataSet()
        _listOfData.postValue(data)
    }
}