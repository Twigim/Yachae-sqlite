package com.example.yachae_sqlite

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteContents:AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var database: SQLiteDatabase

    lateinit var tvContent: TextView

    lateinit var str_content: String

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvContent = findViewById(R.id.tvContent)

        val intent = intent
        str_content = intent.getStringExtra("intent_content").toString()

        dbManager = DBManager(this)
        database = dbManager.readableDatabase

        var cursor: Cursor
        cursor = database.rawQuery("SELECT * FROM noteData WHERE '"+str_content+"';", null)

        if(cursor.moveToNext()){
            str_content = cursor.getString((cursor.getColumnIndex("content"))).toString()
        }

        cursor.close()
        database.close()
        dbManager.close()

        tvContent.text = str_content
    }

}