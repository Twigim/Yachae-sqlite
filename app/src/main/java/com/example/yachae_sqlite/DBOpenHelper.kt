package com.example.yachae_sqlite

import android.database.sqlite.SQLiteOpenHelper
import com.example.yachae_sqlite.DBOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.google.android.material.chip.Chip

class DBOpenHelper(
    customCalendarActivity: CustomCalendarActivity,
    chipName: String,
    context: Context?,
    i: Int
) : SQLiteOpenHelper(context, "challenge.db", null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE chipName (milk TEXT, dairyproduct TEXT, vegetable TEXT, egg TEXT,fish text,meat text, fowls text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //db.execSQL(DROP_EVENTS_TABLE)
        //onCreate(db)
    }

    fun insertData(chipName: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("chipName", chipName)
        val result = db.insert("chipName", null, contentValues)
        if (result.equals(-1))
            return false
        else
            return true
    }
/*
    fun SaveEvent(
        event: String?,
        date: String?,
        month: String?,
        year: String?,
        database: SQLiteDatabase
    ) {
        val contentValues = ContentValues()
        contentValues.put(EVENT, event)
        contentValues.put(DATE, date)
        contentValues.put(MONTH, month)
        contentValues.put(YEAR, year)
        database.insert(EVENT_TABLE_NAME, null, contentValues)
    }*/

/*
    fun SaveEvent(
        event: String?,
        date: String?,
        month: String?,
        year: String?,
        database: SQLiteDatabase
    ) {
        val contentValues = ContentValues()
        contentValues.put(EVENT, event)
        contentValues.put(DATE, date)
        contentValues.put(MONTH, month)
        contentValues.put(YEAR, year)
        database.insert(EVENT_TABLE_NAME, null, contentValues)
    }

    fun ReadEvents(date: String, database: SQLiteDatabase): Cursor {
        val Projections = arrayOf(EVENT, TIME, DATE, MONTH, YEAR)
        val Selection = DATE + "=?"
        val SelectionArgs = arrayOf(date)
        return database.query(
            EVENT_TABLE_NAME,
            Projections,
            Selection,
            SelectionArgs,
            null,
            null,
            null
        )
    }

    fun ReadEventsperMonth(month: String, year: String, database: SQLiteDatabase): Cursor {
        val Projections = arrayOf(EVENT, DATE, MONTH, YEAR)
        val Selection = MONTH + "=? and " + YEAR + "=?"
        val SelectionArgs = arrayOf(month, year)
        return database.query(
            EVENT_TABLE_NAME,
            Projections,
            Selection,
            SelectionArgs,
            null,
            null,
            null
        )
    }

    companion object {
        const val EVENT_TABLE_NAME = "eventstable"
        const val EVENT = "event"
        const val TIME = "time"
        const val DATE = "date"
        const val MONTH = "month"
        const val YEAR = "year"
        private const val DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS " + EVENT_TABLE_NAME
    }*/
}