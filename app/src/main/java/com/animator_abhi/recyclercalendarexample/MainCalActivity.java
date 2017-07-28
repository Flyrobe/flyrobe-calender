package com.animator_abhi.recyclercalendarexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainCalActivity extends AppCompatActivity {
    private RecyclerCalendarView mRecyclerCalendarView;
    TextView todayDate;
  //  String min,max;
    //EditText mind,maxd;
  List<int[]> specialEvents;
    List<int[]> disableDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cal);
        todayDate= (TextView) findViewById(R.id.date1);
        todayDate.setTextSize(24);
        mRecyclerCalendarView= (RecyclerCalendarView) findViewById(R.id.recyclerCalendarView);

        specialEvents=new ArrayList<>();
        disableDates=new ArrayList<>();
        //  todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]+" today is"+mRecyclerCalendarView.getSelectedDate());
    //    mRecyclerCalendarView.setMonthTextView(24);
      //  mRecyclerCalendarView.getHeaderTextView().setTextSize(24);

          todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]);
        mRecyclerCalendarView.setMinDate(2017,8,8);
        mRecyclerCalendarView.setMaxDate(2017,11,28);
        //mRecyclerCalendarView.onSingleSelected(56);
     //   mRecyclerCalendarView.setBgColor(Color.RED);

        int[] eDates={2017,8,23};
        int[] eDates1={2017,8,3};
        int[] eDates2={2017,8,12};
        int[] eDates3={2017,8,9};
        specialEvents.add(eDates);
        specialEvents.add(eDates1);
        specialEvents.add(eDates2);
        specialEvents.add(eDates3);
        mRecyclerCalendarView.setEvent(specialEvents);


        int[] dDates={2017,8,5};
        int[] dDates1={2017,8,4};
        int[] dDates2={2017,8,10};
        int[] dDates3={2017,8,9};
        disableDates.add(dDates);
        disableDates.add(dDates1);
        disableDates.add(dDates2);
        mRecyclerCalendarView.setDisableDates(disableDates);
        mRecyclerCalendarView.setDoubleSelectedMode(false);
      // mRecyclerCalendarView.setPinnedHeaderColor(getResources().getColor(R.color.text_today));
    }


    public void change(View v)
    {  todayDate.setText(""+mRecyclerCalendarView.getSelectedDate()[2]);
        //mRecyclerCalendarView.setBgColor(R.color.colorPrimary);
       // mRecyclerCalendarView.setHeaderTextSize(54);
     /*   boolean doubleSelectedMode = mRecyclerCalendarView.isDoubleSelectedMode();
        mRecyclerCalendarView.setDoubleSelectedMode(!doubleSelectedMode);
        mRecyclerCalendarView.scrollToSelected();*/
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
