package com.example.yachae_sqlite

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.sql.Types.NULL
import java.text.SimpleDateFormat
import java.util.*

class PostActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var edtContent: EditText

    private lateinit var dbManager: DBManager
    private lateinit var database: SQLiteDatabase

    val fragment_community = CommunityFragment()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        dbManager = DBManager(this)
        database = dbManager.writableDatabase

        edtContent = findViewById(R.id.post_content)

        //툴바 설정
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Toolbar에 표시되는 제목의 표시 유무를 설정
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //왼쪽 버튼 사용설정(기본은 뒤로가기)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //왼쪽 버튼 아이콘 변경
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
    }

    //등록 버튼 menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.postactivity_menu, menu)
        return true
    }

    //item 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Fragment", "postActivity")
                startActivity(intent)
            }
            R.id.action_btnPost -> {
                val postContent = edtContent.text.toString()

                val time = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().time)
                val postTime = time.toString()

                val saveData = ContentValues()
                saveData.put("post_content", postContent)
                saveData.put("post_time", postTime)

                //post table에 insert
                database = dbManager.writableDatabase
                database.execSQL("INSERT INTO post VALUES ('" + postContent +"', '"+ postTime +"');")
                database.close()

                val intent = Intent(this, MainActivity::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Fragment", "postActivity")
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
