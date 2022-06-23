package com.example.fooddelivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5.model.SQLiteHelper


enum class Error {
    ERROR_EMAIL,
    ERROR_PASSWORD
}
private lateinit var sqliteHelper: SQLiteHelper

class Resp(val isSuccess: Boolean, val error: Error?)

class LoginVM : ViewModel() {

    private val _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private val _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    fun checkEmailAndPassword(email: String,password: String) {

        val isNotEmptyEmailAndPassword = isEmailAndPasswordNotEmpty(email,password)
        if(!isNotEmptyEmailAndPassword){
            _isErrorEvent.postValue("Enter your Email and Password")
            return
        }
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Email InValid")
            return
        }
        val isValidPassword = isPasswordValid(password)
        if(!isValidPassword) {
            _isErrorEvent.postValue("Password InValid")
            return
        }
        _isSuccessEvent.postValue(true)



    }

    private fun isEmailValid(email: String): Boolean {
        return email.length == 8
    }
    private fun isPasswordValid(password : String ):Boolean{
        return password.length > 8
    }
    private fun isEmailAndPasswordNotEmpty(email: String,password: String):Boolean{
        val passwordNotEmpty = password.isNotEmpty()
        val emailNotEmpty = email.isNotEmpty()
        return passwordNotEmpty && emailNotEmpty
    }


}