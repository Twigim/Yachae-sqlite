package com.example.yachae_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(context: Context?) : SQLiteOpenHelper(context, "yachae.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
    }

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

    fun existsColumnInTable(inTable: String, columnToCheck: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM $inTable LIMIT 0", null)

        return if (cursor.getColumnIndex(columnToCheck) != -1) true else false
    }

    companion object {
        const val DB_NAME = "yachae.db"
    }
}