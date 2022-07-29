package com.example.yachae_sqlite

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class NoteMainActivity : AppCompatActivity() {

        private val tableName: String = "noteData"

        private lateinit var dbManager: DBManager
        private lateinit var database: SQLiteDatabase

        //lateinit var noteRecycler: RecyclerView
        lateinit var textView: TextView

        @SuppressLint("Range")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_note_main)
            dbManager = DBManager(this)
            database = dbManager.readableDatabase

            textView = findViewById(R.id.tvContent)

            val newNotepad: Button = findViewById(R.id.newNoteButton_main)
            newNotepad.setOnClickListener {
                startActivity(Intent(this, NoteReg::class.java))
                finish()

            var cursor: Cursor
            cursor = database.rawQuery("SELECT * FROM noteData;", null)

            var num:Int = 0
            while(cursor.moveToNext()){
                var str_content = cursor.getString(cursor.getColumnIndex("content")).toString()

                var tvContent: TextView = TextView(this)
                tvContent.text = str_content
                tvContent.setPadding(10,10,10,0)

                num++
            }
            cursor.close()
            database.close()
            dbManager.close()

            }
        }
    }
