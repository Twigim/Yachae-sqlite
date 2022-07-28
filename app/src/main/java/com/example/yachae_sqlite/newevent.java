package com.example.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class newevent extends AppCompatActivity{
    Chip chip_milk=findViewById(R.id.chip_milk);
   Button AddEvent=findViewById(R.id.addevent);

   public void CustomCalendarView(){
       if(chip_milk.isChecked()==true)
       {
           AddEvent.setBackgroundColor(getResources().getColor(R.color.purple_200));
       }

   }



}
