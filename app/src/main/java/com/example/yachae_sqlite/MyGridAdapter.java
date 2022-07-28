package com.example.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdapter extends ArrayAdapter {
    List<Date> dates;
    Calendar currentDate;
    LayoutInflater inflater;
    LocalDate selectedDate;
    private Calendar mCal;

    public MyGridAdapter(@NonNull Context context, List<Date> dates, Calendar currentDate, List<Events> eventsList) {
        super(context, R.layout.single_cell_layout);
        this.dates=dates;
        this.currentDate=currentDate;
        inflater=LayoutInflater.from(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate=dates.get(position);
        Calendar dateCalendar=Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo=dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth=dateCalendar.get(Calendar.MONTH)+1;
        int displayYear=dateCalendar.get(Calendar.YEAR);
        int displayDay=dateCalendar.get(Calendar.DATE);
        int currentMonth=currentDate.get(Calendar.MONTH)+1;
        int currentYear=currentDate.get(Calendar.YEAR);
        int currentDay=currentDate.get(Calendar.DATE);
        selectedDate=LocalDate.now();

        View view=convertView;
        if(view==null){
            view=inflater.inflate(R.layout.single_cell_layout,parent,false);
        }

        if(displayMonth==currentMonth&&displayYear==currentYear&&displayDay==currentDay){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
       }

        //gridview크기 조절:https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jjjjokk&logNo=220678981673
        // view.setLayoutParams(new AbsListView.LayoutParams(130, 130));
        TextView Day_Number=view.findViewById(R.id.calendar_day);
        Day_Number.setText(String.valueOf(DayNo));
        Day_Number.setTextSize(Dimension.SP,12);

        if(displayMonth==currentMonth&&displayYear==currentYear){
            Day_Number.setTextColor(Color.parseColor("#000000"));
        }
        else
        {
            Day_Number.setTextColor(Color.parseColor("#D2CDCD"));
        }

        //ArrayList<String> arrayList=new ArrayList<>();
        //for(int i=0;i<)

        return view;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}
