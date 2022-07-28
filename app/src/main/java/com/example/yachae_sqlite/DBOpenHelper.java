package com.example.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="EVENTS_DB";
    public static final int DB_VERSION=1;
    public static final String EVENT_TABLE_NAME="eventstable";
    public static final String EVENT="event";
    public static final String TIME="time";
    public static final String DATE="date";
    public static final String MONTH="month";
    public static final String YEAR="year";

    private static final  String CREATE_EVENTS_TABLE="create table "+EVENT_TABLE_NAME+
        "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+EVENT+" TEXT, "+DATE+" TEXT, "+MONTH+" TEXT, "
            +YEAR+" TEXT)";

    private static final  String DROP_EVENTS_TABLE="DROP TABLE IF EXISTS "+EVENT_TABLE_NAME;


    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);
    }

    public void SaveEvent(String event, String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues= new ContentValues();
        contentValues.put(EVENT,event);
        contentValues.put(DATE,date);
        contentValues.put(MONTH,month);
        contentValues.put(YEAR,year);
        database.insert(EVENT_TABLE_NAME,null,contentValues);

    }

    public Cursor ReadEvents(String date, SQLiteDatabase database){
     String [] Projections={EVENT,TIME,DATE,MONTH,YEAR};
     String Selection=DATE+"=?";
     String [] SelectionArgs = {date};

     return database.query(EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }

    public Cursor ReadEventsperMonth(String month,String year, SQLiteDatabase database){
        String [] Projections={EVENT,DATE,MONTH,YEAR};
        String Selection=MONTH+"=? and "+YEAR+"=?";
        String [] SelectionArgs = {month, year};

        return database.query(EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
}
