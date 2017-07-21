package com.animator_abhi.recyclercalendarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.animator_abhi.recyclerviewcalendar.recycleCal.MyRecyclerCalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
//CalendarView cl;
   // TextView tv;
    MyRecyclerCalendarView myRecyclerCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main2);

          myRecyclerCalendarView=new MyRecyclerCalendarView(this);
        setContentView(myRecyclerCalendarView);
       // cl= (CalendarView) findViewById(R.id.myCal);
       // cl.setFirstDayOfWeek(2);
//Calendar c=Calendar.getInstance();
//cl.getFirstDayOfWeek();

        //tv= (TextView) findViewById(R.id.textView1);
       // tv.setText(""+cl.getDate()+"val "+  c.getFirstDayOfWeek()+" "+ cl.getMaxDate()+" "+cl.getMinDate()+
       // " ");


    }
}
