package com.animator_abhi.recyclercalendarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView;

public class MainCalActivity extends AppCompatActivity {
    private RecyclerCalendarView mRecyclerCalendarView;
    TextView todayDate;
  //  String min,max;
    //EditText mind,maxd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cal);
        todayDate= (TextView) findViewById(R.id.date1);
        todayDate.setTextSize(24);
        mRecyclerCalendarView= (RecyclerCalendarView) findViewById(R.id.recyclerCalendarView);


        //  todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]+" today is"+mRecyclerCalendarView.getSelectedDate());
        mRecyclerCalendarView.setMonthTextView(24);
        mRecyclerCalendarView.getHeaderTextView().setTextSize(24);

          todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]);
        mRecyclerCalendarView.setMinDate(2015,1);
        //mRecyclerCalendarView.onSingleSelected(56);


    //    mRecyclerCalendarView.setPinnedHeaderColor(getResources().getColor(R.color.text_today));
    }


    public void change(View v)
    {
        mRecyclerCalendarView.setBgColor(R.color.colorPrimary);
       // mRecyclerCalendarView.setHeaderTextSize(54);

      //  min(Integer.parseInt(mind.getText().toString()));
      //  max(Integer.parseInt(maxd.getText().toString()));




    }

    /*public void min(int min)
    {
        mRecyclerCalendarView.setMinDate(min,1);
    }
    public void max(int max) {
        mRecyclerCalendarView.setMaxDate(max, 12);
    }*/

}
