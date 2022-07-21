package com.example.yachae_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, "notepad_kot.db", null, 1) {

    //생성
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table if not exists noteData(" +
                "_id integer PRIMARY KEY autoincrement," +
                "title text," +
                "content text," +
                "time text)"

        db.execSQL(sql)
    }

    //수정
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS noteData")
        }
    }
}