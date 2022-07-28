package com.example.calendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CalendarMainActivity : AppCompatActivity() {
    lateinit var customCalendarActivity: CustomCalendarActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customCalendarActivity = findViewById<View>(R.id.custom_calendar_view) as CustomCalendarActivity
    }
}