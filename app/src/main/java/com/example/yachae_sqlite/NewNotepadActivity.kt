package com.example.yachae_sqlite

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class NewNotepadActivity : AppCompatActivity() {

    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private lateinit var edtTitle: EditText
    private lateinit var edtContent: EditText

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notepad)

        dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase
        tableName = "noteData"

        btnSave = findViewById(R.id.btn_save)
        btnCancel = findViewById(R.id.btn_cancel)

        edtTitle = findViewById(R.id.title_newNotepad)
        edtContent = findViewById(R.id.contents_newNotepad)

        //save
        btnSave.setOnClickListener {
            if (edtTitle.text.isEmpty() && edtContent.text.isEmpty()) {
                Toast.makeText(this, "작성한 내용이 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val title = edtTitle.text.toString()
                val content = edtContent.text.toString()

                //메모 시간, 날짜
                val sdfNow = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().time)
                val time = sdfNow.toString()

                //ContentValues()클래스는 ContentResolver에 데이터를 저장
                val saveData = ContentValues()

                saveData.put("title", title)
                saveData.put("content", content)
                saveData.put("time", time)

                database.insert(tableName, null, saveData)

                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}