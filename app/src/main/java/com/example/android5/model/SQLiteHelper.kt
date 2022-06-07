package com.example.android5.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android. database.sqlite.SQLiteDatabase
import android.database.sqlite. SQLiteOpenHelper
import androidx.fragment.app.Fragment
import com.example.android5.SignupFragment

class SQLiteHelper(fragment: Fragment) : SQLiteOpenHelper(fragment.requireContext(), DATABASE_NAME,null , DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PASS = "pass"
        private const val PASSCOMFIRM = "passconfirm"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent =
            ("CREATE TABLE " + TBL_STUDENT + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + EMAIL + " TEXT," + PASS + " TEXT"  + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std: StudentModel): Long {
        val db = this.writableDatabase

        val contextValues = ContentValues()
        //contextValues.put(ID, std.id)
        contextValues.put(NAME, std.name)
        contextValues.put(EMAIL, std.email)
        contextValues.put(PASS, std.pass)
        val success = db.insert(TBL_STUDENT, null, contextValues)
        db.close()
        return success
    }

    fun updateUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(NAME, user.name)
        values.put(EMAIL, user.email)
        values.put(PASS, user.password)

        // updating row
        db.update(
            TBL_STUDENT, values, "$ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }
    fun deleteUser(user: User) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(
            TBL_STUDENT, "$ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }


    @SuppressLint("Range")
    fun getAllStudent(): ArrayList<StudentModel> {
        val stdList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String
        var pass: String
        var passconfirm:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                pass = cursor.getString(cursor.getColumnIndex("pass"))


                val std = StudentModel(id = id, name = name, email = email, pass = pass)
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList
    }


    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkUser(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$EMAIL = ?"
        // selection argument
        val selectionArgs = arrayOf(email)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(
            TBL_STUDENT, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$TBL_STUDENT = ? AND $PASS = ?"
        // selection arguments
        val selectionArgs = arrayOf(email, password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(
            TBL_STUDENT, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }

}