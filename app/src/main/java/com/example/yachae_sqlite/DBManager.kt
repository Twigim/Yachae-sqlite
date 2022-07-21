package com.example.kapil.kotlindemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Kapil on 3/31/2018.
 */
class DBManager(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val tableName :String ="user"
    val ID: String = "_id"
    val EMAIL: String = "email"
    val PASSWORD: String = "password"
    companion object {

        val DATABASE_VERSION = 1
        val DATABASE_NAME = "user.db"


    }

    public val SQL_CREATE_ENTRIES:String ="CREATE TABLE IF NOT EXISTS "+ tableName + "( ${ID} integer PRIMARY KEY autoincrement," +
            "${EMAIL} text, ${PASSWORD} text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertIntoTable(tableName:String,contentValues: ContentValues){
        getWritableDatabase().insert(tableName,null,contentValues)
    }
    fun  GetDetaislCursor(lquerry:String):Cursor{
        var cursor : Cursor =readableDatabase.rawQuery(lquerry,null)
        return cursor
    }
}