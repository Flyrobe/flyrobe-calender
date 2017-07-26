package com.animator_abhi.recyclercalendarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView;

public class MainCalActivity extends AppCompatActivity {
    private RecyclerCalendarView mRecyclerCalendarView;
    TextView todayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cal);
        todayDate= (TextView) findViewById(R.id.date1);
        todayDate.setTextSize(24);
        mRecyclerCalendarView= (RecyclerCalendarView) findViewById(R.id.recyclerCalendarView);


        //  todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]+" today is"+mRecyclerCalendarView.getSelectedDate());
        mRecyclerCalendarView.setMonthTextView(24);


          todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]);
        //mRecyclerCalendarView.onSingleSelected(56);


    //    mRecyclerCalendarView.setPinnedHeaderColor(getResources().getColor(R.color.text_today));
    }


    public void change(View v)
    {
        mRecyclerCalendarView.setBgColor(R.color.colorPrimary);
        mRecyclerCalendarView.setHeaderTextSize(54);
        mRecyclerCalendarView.getHeaderTextView().setTextSize(24);
        mRecyclerCalendarView.setMinDate(2016,5);
        mRecyclerCalendarView.setMaxDate(2016,8);


    }
}
