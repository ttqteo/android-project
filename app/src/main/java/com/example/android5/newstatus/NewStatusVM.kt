package com.example.android5.newstatus

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android5.MySharedpreferences
import com.example.android5.database.Feed
import com.example.android5.database.FeedRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class NewStatusVM(private val feedRepository: FeedRepository) : ViewModel() {

    fun addFeed(
        name: String,
        hour: String,
        status: String,
        title_status: String,
        link_home_web: String,
    ) {
        val user = hashMapOf(
            "name" to name,
            "hour" to hour,
            "status" to status,
            "title_status" to title_status,
            "link_home_web" to link_home_web
        )
        val db = Firebase.firestore
        var max = db.collection("maxfeed").document("max")
        max.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val valuemax = document["max"]
                    val st:String
                    st= valuemax.toString()
                    val inttt:Int
                    inttt=st.toInt()

                    if ( inttt != null) {
                        db.collection("feed").document((inttt + 1).toString())
                            .set(user)
                            .addOnSuccessListener {
                                Log.d(
                                    ContentValues.TAG,
                                    "DocumentSnapshot successfully written!"
                                )
                            }
                            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
                    }
                    val idgrow = hashMapOf(
                        "max" to (inttt?.plus(1))
                    )
                    db.collection("maxfeed").document("max")
                        .set(idgrow)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }
}

//fun addFeed(name: String,
//            hour: String,
//            status: String,
//            title_status: String,
//            link_home_web: String,) {
//    viewModelScope.launch {
//        feedRepository.addFeed(name,hour,status,title_status,link_home_web)
//    }
//}