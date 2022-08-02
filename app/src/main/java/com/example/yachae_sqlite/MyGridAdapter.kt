package com.example.yachae_sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

class MyGridAdapter(
    context: Context, var dates: List<Date>, var currentDate: Calendar)
    : ArrayAdapter<Any?>(context, R.layout.single_cell_layout) {
    var inflater: LayoutInflater

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //날짜 변수에 담기
        val monthDate = dates[position]
        //달력 초기화(오늘 날짜로)
        val currentCalendar = Calendar.getInstance()
        //달력을 해당 달의 날로 set
        currentCalendar.time = monthDate

        val DayNo = currentCalendar[Calendar.DAY_OF_MONTH]

        //넘어온 데이터
        val displayMonth = currentCalendar[Calendar.MONTH] + 1 //현재월
        val displayYear = currentCalendar[Calendar.YEAR] //현재 년도
        val displayDay = currentCalendar[Calendar.DATE] //현재월의 날짜
        //현재 년,월
        val currentMonth = currentDate[Calendar.MONTH] + 1
        val currentYear = currentDate[Calendar.YEAR]
        val currentDay = currentDate[Calendar.DATE]

        val selectedDate = LocalDate.now()
        val month = selectedDate.monthValue
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.single_cell_layout, parent, false)
        }
        //현재 날짜의 배경색 변경
        if (displayMonth == month && displayDay == currentDay) {
            view!!.setBackgroundResource(R.drawable.calendarborder)
        }

        //gridview크기 조절
        view!!.layoutParams = AbsListView.LayoutParams(130, 130)

        //텍스트 설정
        val Day_Number = view.findViewById<TextView>(R.id.calendar_day)
        Day_Number.text = DayNo.toString()
        Day_Number.setTextSize(Dimension.SP, 12f)

        //비교해서 월,날짜 맞으면 진한색
        if (displayMonth == currentMonth && displayYear == currentYear) {
            Day_Number.setTextColor(Color.parseColor("#000000"))
        }
        else {
            Day_Number.setTextColor(Color.parseColor("#D2CDCD"))
        }
        return view
    }

    override fun getCount(): Int {
        return dates.size
    }

    override fun getPosition(item: Any?): Int {
        return dates.indexOf(item)
    }

    override fun getItem(position: Int): Any? {
        return dates[position]
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}