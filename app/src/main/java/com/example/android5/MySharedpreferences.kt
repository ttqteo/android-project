package com.example.android5

import android.content.Context
import android.content.SharedPreferences

object MySharedpreferences {

    private lateinit var sharedPreferences: SharedPreferences

    private const val PREF_NAME = "shared_preference"

    enum class KEY(val value: String) {
        KEY_USERNAME("KEY_USERNAME"),
        KEY_PASS("KEY_PASS"),
        KEY_MES("KEY_MES"),
        KEY_MESS("KEY_MESS"),
        KEY_INT("KEY_INT")
    }

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUsername(username: String) {
        sharedPreferences.edit().putString(KEY.KEY_USERNAME.value, username).apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY.KEY_USERNAME.value, null)
    }

    fun savePass(password: String){
        sharedPreferences.edit().putString(KEY.KEY_PASS.value, password).apply()
    }

    fun getPass() : String? {
        return sharedPreferences.getString(KEY.KEY_PASS.value, "")
    }
    fun deleteall(){
        sharedPreferences.edit().clear().apply()
    }
    fun savemes(string:String){
        sharedPreferences.edit().putString(KEY.KEY_MES.value, string).apply()
    }
    fun getmes() : String? {
        return sharedPreferences.getString(KEY.KEY_MES.value, "")
    }
    fun savemess(string:String){
        sharedPreferences.edit().putString(KEY.KEY_MESS.value, string).apply()
    }
    fun getmess() : String? {
        return sharedPreferences.getString(KEY.KEY_MESS.value, "")
    }
    fun saveint(int:Int){
        sharedPreferences.edit().putInt(KEY.KEY_INT.value, int).apply()
    }
    fun getint() : Int? {
        return sharedPreferences.getInt(KEY.KEY_INT.value, -1)
    }

}