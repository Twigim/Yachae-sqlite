package com.example.yachae_sqlite

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private val tableName:String = "noteData"

    private lateinit var dbHelper: SQLiteOpenHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseCreate()    //데이터베이스생성
        createTable()       //테이블 생성

        database = dbHelper.readableDatabase



        //val newNotepad: ImageButton = findViewById(R.id.newNoteButton_main)
        //이미지버튼 클릭
        val newNotepad: Button = findViewById(R.id.newNoteButton_main)
        newNotepad.setOnClickListener {
            //NewNotepadActivity 클래스 실행 (새 메모)
            startActivity(Intent(this, NewNotepadActivity::class.java))
            finish()
        }


    }

    private fun createTable() {
        database.execSQL(
            "create table if not exists ${tableName}(" +
                    "_id integer PRIMARY KEY autoincrement," +
                    "title text," +
                    "content text," +
                    "time text)"
        )
    }

    private fun databaseCreate() {
        // 데이터베이스 생성 | 쓰기 가능한 상태로 설정
        dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase
    }
}