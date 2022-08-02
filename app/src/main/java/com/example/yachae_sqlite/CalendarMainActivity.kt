package com.example.yachae_sqlite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.yachae_sqlite.CustomCalendarActivity
import com.example.yachae_sqlite.R

class CalendarMainActivity : AppCompatActivity() {

    lateinit var customCalendarActivity: CustomCalendarActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_challenge)

        customCalendarActivity = findViewById<View>(R.id.custom_calendar_view) as CustomCalendarActivity
    }
}