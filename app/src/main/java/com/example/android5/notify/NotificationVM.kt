package com.example.android5.notify

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.DataStoreNoti
import com.example.android5.database.FeedRepository
import com.example.android5.model.Noti
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class NotificationVM (private val feedRepository: FeedRepository) : ViewModel() {
    private var _listOfData: MutableLiveData<List<Noti>> = MutableLiveData()
    val listOfData: LiveData<List<Noti>>
        get() = _listOfData
    val db = Firebase.firestore
    private lateinit var arraynoti:ArrayList<Noti>

    fun loadData() {
        arraynoti= arrayListOf()
        db.collection("feed")
            .orderBy("hour", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges) {
                    arraynoti.add(dc.document.toObject(Noti::class.java))
                    viewModelScope.launch {
                        //var data=getdatafirebasestore()
                        //val data = feedRepository.readnotifydata()
                        _listOfData.postValue(arraynoti)
                    }
                }
                Log.d(TAG, "Current cites in CA: $arraynoti")
            }
    }
}