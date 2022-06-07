package com.example.fooddelivery

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5.SignupFragment
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
//        val isCorrect = correctEmailAndPassword(email,password)
//        if(!isCorrect) {
//            _isErrorEvent.postValue("Wrong account")
//            return
//        }
//        var db = sqliteHelper.readableDatabase
//        var args = listOf<String>(email).toTypedArray()
//        var rs = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE EMAIL = ?",args)
//        if (!(rs.moveToNext())) {
//            _isErrorEvent.postValue("Email không tồn tại")
//            return
//        }
//
//        var args1 = listOf<String>(password).toTypedArray()
//        var rs1 = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE PASS = ?",args1)
//        if (!(rs1.moveToNext())) {
//            _isErrorEvent.postValue("Password khong dung")
//            return
//        }
//        var args2 = listOf<String>(email,password).toTypedArray()
//        var rs2 = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE EMAIL = ? AND PASS = ?",args2)
//        if (rs2.moveToNext()) {
//            _isErrorEvent.postValue("Incorrect Email or Password")
//            return
//        }
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