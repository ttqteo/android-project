package com.example.android5.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.DataStoreFeed
import com.example.android5.database.FeedRepository
import kotlinx.coroutines.launch
import com.example.android5.model.Feed
import com.example.android5.model.Noti
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeVM(private val feedRepository: FeedRepository) : ViewModel() {

    private var _listOfData: MutableLiveData<List<Feed>> = MutableLiveData()
    val listOfData: LiveData<List<Feed>>
        get() = _listOfData
    val db = Firebase.firestore
    private lateinit var arrayfeed:ArrayList<Feed>

    fun loadData() {
//        viewModelScope.launch {
//            val data = feedRepository.readalldata()
//            _listOfData.postValue(data)
//        }
        arrayfeed= arrayListOf()
        db.collection("feed")
            .orderBy("hour", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(ContentValues.TAG, "listen:error", e)
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges) {
                    arrayfeed.add(dc.document.toObject(Feed::class.java))
                    viewModelScope.launch {
                        //var data=getdatafirebasestore()
                        //val data = feedRepository.readnotifydata()
                        _listOfData.postValue(arrayfeed)
                    }
                }
                Log.d(ContentValues.TAG, "arrayfeed: $arrayfeed")
            }
    }
}