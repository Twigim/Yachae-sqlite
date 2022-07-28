package com.example.calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomCalendarActivity extends LinearLayout {
    Button NextButton,PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS=42;
    Calendar calendar=Calendar.getInstance(Locale.KOREAN);
    Context context;
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy MMMM dd",Locale.KOREAN);
    SimpleDateFormat monthFormat= new SimpleDateFormat("MMMM",Locale.KOREAN);
    SimpleDateFormat yearFormat= new SimpleDateFormat("yyyy",Locale.KOREAN);
    Chip chip_milk,chip_dairyproduct,chip_vegetable,chip_egg,chip_fish,chip_meat;

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates= new ArrayList<>();
    List<Events> eventsList= new ArrayList<>();
    DBOpenHelper dbOpenHelper;

    public CustomCalendarActivity(Context context) {
        super(context);
    }
    public CustomCalendarActivity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        IntializeLayout();
        SetUpCalendar();

        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,-1);
                SetUpCalendar();
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View addView=LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout,null);
               // EditText EventName=addView.findViewById(R.id.events_id);
                //TextView EventTime=addView.findViewById(R.id.eventtime);

                Button AddEvent=addView.findViewById(R.id.addevent);
                builder.setView(addView);
                alertDialog=builder.create();
                alertDialog.show();

                AddEvent.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v){
                       //SaveEvent(EventName.getText().toString(),EventName.getText().toString());
                        // SetUpCalendar();
                        alertDialog.dismiss();
                       view.setBackgroundResource(R.drawable.stamp3black);
                    }
                });
            }
        });
    }


    public CustomCalendarActivity(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    private void SaveEvent(String event, String date,String month,String year){
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,date,month,year,database);
    }

    private void IntializeLayout(){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.calendar_layout,this);
        PreviousButton=view.findViewById(R.id.previousBtn);
        NextButton=view.findViewById(R.id.nextBtn);
        CurrentDate=view.findViewById(R.id.currnet_Date);
        gridView=view.findViewById(R.id.gridview);

        chip_milk=findViewById(R.id.chip_milk);
        chip_dairyproduct=findViewById(R.id.chip_dairyproduct);
        chip_vegetable=findViewById(R.id.chip_vegetable);
        chip_egg=findViewById(R.id.chip_egg);
        chip_fish=findViewById(R.id.chip_fish);
        chip_meat=findViewById(R.id.chip_meat);
    }

    private void SetUpCalendar(){
       String currentDate=dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar=(Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayofMonth=monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDayofMonth);

        while (dates.size()<MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }

        myGridAdapter=new MyGridAdapter(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridAdapter);
    }
}
