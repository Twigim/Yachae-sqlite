package com.example.yachae_sqlite

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(){

    lateinit var detailToolbar : androidx.appcompat.widget.Toolbar

    //lateinit var UpdateDetailContent : EditText
    //lateinit var detailContent : String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        //UpdateDetailContent = findViewById(R.id.textContent);

        //getAndSetIntentData()

        //Toolbar
        detailToolbar = findViewById(R.id.detail_toolbar)
        setSupportActionBar(detailToolbar)
        //Toolbar에 표시되는 제목의 표시 유무
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 뒤로가기
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

//    fun getAndSetIntentData() {
//        if(getIntent().hasExtra("detailContent")){
//
//            //데이터 가져오기
//            detailContent = getIntent().getStringExtra("detailContent").toString()
//
//            //데이터 넣기
//            UpdateDetailContent.setText(detailContent)
//            }
//        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
