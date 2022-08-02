package com.example.yachae_sqlite

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(){

    lateinit var detailToolbar : androidx.appcompat.widget.Toolbar

    lateinit var UpdateDetailContent : TextView
    lateinit var detailContent : String
    lateinit var UpdateDateTime : TextView
    lateinit var DateTime : String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        UpdateDetailContent = findViewById(R.id.update_detail_content)
        UpdateDateTime = findViewById(R.id.update_datetime)

        //getAndSetIntentData()

        //Toolbar
        detailToolbar = findViewById(R.id.detail_toolbar)
        setSupportActionBar(detailToolbar)
        //Toolbar에 표시되는 제목의 표시 유무
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 뒤로가기
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

        getAndSetIntentData()

        detailContent = getIntent().getStringExtra("detailContent").toString()
        DateTime = getIntent().getStringExtra("DateTime").toString()

        Log.d("test",detailContent)
        Log.d("test",DateTime)

    }

    //데이터 가져와서 화면에 보여주기
    fun getAndSetIntentData() {
        if(getIntent().hasExtra("detailContent")){
            //데이터 가져오기
            detailContent = getIntent().getStringExtra("detailContent").toString()
            DateTime = getIntent().getStringExtra("DateTime").toString()

            Log.d("test",detailContent)
            Log.d("test",DateTime)

            //데이터 넣기
            UpdateDetailContent.setText(detailContent)
            UpdateDateTime.setText(DateTime)
            }
        }

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
