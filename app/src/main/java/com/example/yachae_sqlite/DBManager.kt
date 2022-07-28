package com.example.yachae_sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(context: Context?) : SQLiteOpenHelper(context, "yachae.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
        MyDB.execSQL("create Table post(post_content TEXT)")
        //MyDB.execSQL("create Table noteData(content TEXT)")
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

    //select 메소드
    @SuppressLint("Range")
    fun selectPost():MutableList<PostList>{
        val list = mutableListOf<PostList>()
        //전체조회
        val selectAll = "select * from post"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        //반복문을 사용하여 list 에 데이터를 넘겨 줍시다.
        while(cursor.moveToNext()){
            val content = cursor.getString(cursor.getColumnIndex("post_content"))

            list.add(PostList(content))
        }
        cursor.close()
        rd.close()

        return list
    }

    companion object {
        const val DB_NAME = "yachae.db"
    }
}