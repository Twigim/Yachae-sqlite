package com.example.yachae_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBManager(context: Context?) : SQLiteOpenHelper(context, "yachae.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, veg_type TEXT)")
        MyDB.execSQL("insert into users(username, password, veg_type) values('1', '1', '1')")

    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
    }

//    override fun onUpgrade(MyDB: SQLiteDatabase, a_oldVersion: Int, a_newVersion: Int) {
//        try {
//            if (a_oldVersion == 4 ) {
//                updateVegTypeColumn(MyDB)
//            }
//        } catch (e: SQLException) {
//            MyDB.execSQL("drop Table if exists users")
//            onCreate(MyDB)
//        }
//    }

    fun insertData(username: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        if (result.equals(-1))
            return false
        else
            return true
    }

//    fun insertVegTypeData(username: String?, password: String?, veg_type : String?): Boolean {
//        val MyDB = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put("username", username)
//        contentValues.put("password", password)
//        contentValues.put("veg_type", veg_type)
//        val result = MyDB.insert("users", null, contentValues)
//        if (result.equals(-1))
//            return false
//        else
//            return true
//    }

//    fun insertVegTypeData(veg_type : String?): Boolean {
//        val MyDB = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put("veg_type", veg_type)
//        val result = MyDB.insert("users", null, contentValues)
//        if (result.equals(-1))
//            return false
//        else
//            return true
//    }

//    fun updateVegType(username: String, password: String, veg_type : String ) {
//        val MyDB = this.writableDatabase
//        val query: String = "users" +
//                "username" + "=" + username + " " +
//                "password" + "=" + password + " " +
//                "veg_type" + "=" + veg_type + " " +
//                "WHERE " + "username" + "=" + username
//        MyDB.execSQL(query)
//    }

    fun updateVegTypeData(username: String, password: String, veg_type: String): Boolean? {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        contentValues.put("veg_type", veg_type)
        sqLiteDatabase.update("users", contentValues, username.toString() + "= ?", arrayOf(username))
        return true
    }



    // 존재하는 username인지 확인
    fun checkUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        return if (cursor.count > 0) true else false
    }

    fun checkUsernamePassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where username = ? and password = ?",
            arrayOf(username, password)
        )
        return if (cursor.count > 0) true else false
    }


//    fun updateVegTypeColumn(MyDB: SQLiteDatabase) {
//        MyDB.execSQL("ALTER TABLE users ADD COLUMN veg_type TEXT")
//    }

    companion object {
        const val DB_NAME = "yachae.db"
    }
}