package com.example.android5.model

import androidx.room.PrimaryKey
import kotlin.random.Random

data class StudentModel (
     var id: Int = getAutoId(),
    var name: String = "",
    var email: String = "",
    var pass: String = "",
    var passconfirm :String = "",
    ){
    companion object{
        fun getAutoId():Int{
            val random = Random
            return random.nextInt(10000)
        }
    }
}