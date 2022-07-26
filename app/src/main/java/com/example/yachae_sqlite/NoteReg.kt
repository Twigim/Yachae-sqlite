package com.example.yachae_sqlite

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NoteReg : AppCompatActivity() {

    private lateinit var dbManager: DBManager
    private lateinit var database: SQLiteDatabase

    private lateinit var btnSave: Button
    private lateinit var edtContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_reg)

        btnSave = findViewById(R.id.btn_save)
        edtContent = findViewById(R.id.edit_content)

        dbManager = DBManager(this)

        //save
        btnSave.setOnClickListener {
            if (edtContent.text.isEmpty()) {
                Toast.makeText(this, "작성한 내용이 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                var str_content: String = edtContent.text.toString()

//                //메모 시간, 날짜
//                val sdfNow = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().time)
//                val time = sdfNow.toString()

                database = dbManager.writableDatabase
                database.execSQL("INSERT INTO noteData VALUES ('"+str_content+"')")
                database.close()


                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("intent_content", str_content)
                startActivity(intent)
            }
        }
    }
}