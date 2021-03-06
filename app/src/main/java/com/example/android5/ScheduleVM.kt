package com.example.android5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5.model.Schedule

class ScheduleVM : ViewModel() {
    private var _listOfData: MutableLiveData<List<Schedule>> = MutableLiveData()
    val listOfData: LiveData<List<Schedule>>
        get() = _listOfData

    fun loadData() {
        val data = DataStoreSchedule.getDataSet()
        _listOfData.postValue(data)
    }
}