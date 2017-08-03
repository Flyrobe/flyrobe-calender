package com.animator_abhi.recyclercalendarexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainCalActivity extends AppCompatActivity {
    private RecyclerCalendarView mRecyclerCalendarView;
    TextView todayDate;
  static int i=0;
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
    //    mRecyclerCalendarView.setMonthTextViewSize(24);
      //  mRecyclerCalendarView.getHeaderTextView().setTextSize(24);

          todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]);
      //  mRecyclerCalendarView.setMinDate(2017,8,8);

        //mRecyclerCalendarView.onSingleSelected(56);
     //   mRecyclerCalendarView.setBgColor(Color.RED);

        int[] eDates={2017,8,2};
        int[] eDates1={2017,8,3};
        int[] eDates2={2017,8,12};
        int[] eDates3={2017,8,9};
        specialEvents.add(eDates);
        specialEvents.add(eDates1);
        specialEvents.add(eDates2);
        specialEvents.add(eDates3);
        mRecyclerCalendarView.setEvent(specialEvents);


        int[] dDates={2017,8,2};
        int[] dDates1={2017,8,4};
        int[] dDates2={2017,8,10};
        int[] dDates3={2017,8,9};
        disableDates.add(dDates);
        disableDates.add(dDates1);
        disableDates.add(dDates2);
        mRecyclerCalendarView.setDisableDates(disableDates);
        Log.d("block dates",""+disableDates);
        mRecyclerCalendarView.setDoubleSelectedMode(false);
        mRecyclerCalendarView.showMonthHeader(true);
        mRecyclerCalendarView.showMonthHeader(true);

        // mRecyclerCalendarView.setPinnedHeaderColor(getResources().getColor(R.color.text_today));
    }


    public void change(View v)
    { // todayDate.setText(""+mRecyclerCalendarView.getSelectedDate()[2]);


    //  mRecyclerCalendarView.setDecoratorItem(R.drawable.ic_my_selector);
      switch(i)
      {
        case 0:
            mRecyclerCalendarView.getHeaderTextView().setBackgroundColor(Color.YELLOW);
//mRecyclerCalendarView.resetCalendar();
             mRecyclerCalendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.primary_darker_blue));

           mRecyclerCalendarView.setWeekendDayColor(getResources().getColor(R.color.saved_event_selector_color));
            Toast.makeText(this, "weekend color and background_day_color", Toast.LENGTH_SHORT).show();
          i++;
          break;
        case 1:
           // mRecyclerCalendarView.getHeaderTextView().setTextSize(32);
          mRecyclerCalendarView.setEventColor(Color.BLUE);
            mRecyclerCalendarView.setMonthDividerVisible(true);
            Toast.makeText(this, "setEventColor", Toast.LENGTH_SHORT).show();
            mRecyclerCalendarView.getHeaderTextView().setTextColor(Color.GREEN);
            mRecyclerCalendarView.setDividerColor(Color.RED);
          i++;
          break;
        case 2:
            Toast.makeText(this, "setSelectionDayColor", Toast.LENGTH_SHORT).show();

            // mRecyclerCalendarView.setBackgroundDayColor(Color.CYAN);//
           mRecyclerCalendarView.setSelectionDayColor(Color.BLACK);
            mRecyclerCalendarView.setDividerColor(getResources().getColor(R.color.primary_darker_blue),true,true,true);
          i++;
          break;
        case 3:
            mRecyclerCalendarView.setPinnedHeaderColor(Color.RED);
        //  mRecyclerCalendarView.setDayColor(Color.YELLOW);
            Toast.makeText(this, "setDayColor", Toast.LENGTH_SHORT).show();
           // mRecyclerCalendarView.setDisableDates(disableDates);
          i++;
          break;
        case 4:
          //  mRecyclerCalendarView.setDisableDates(disableDates);
            Toast.makeText(this, "setDisableDates", Toast.LENGTH_SHORT).show();
        //    mRecyclerCalendarView.getPinnedHeaderView().setForegroundGravity(0);
mRecyclerCalendarView.setMonthTextViewSize(32);
          i++;
          break;
        case 5:
         mRecyclerCalendarView.setTodayColor(Color.GREEN);
            Toast.makeText(this, "setTodayColor", Toast.LENGTH_SHORT).show();

          i++;
          break;
        case 6:

            mRecyclerCalendarView.setDisableDayColor(Color.GRAY);
            Toast.makeText(this, "setDisableDayColor", Toast.LENGTH_SHORT).show();
          i++;
          break;
        case 7:
            mRecyclerCalendarView.setMinDate(2017,8,3);
            Toast.makeText(this, "setMinDate", Toast.LENGTH_SHORT).show();

            i++;
          break;
        case 8:
         // mRecyclerCalendarView.setBackgroundRangeColor(Color.GRAY);
            mRecyclerCalendarView.setMaxDate(2017,11,28);
            Toast.makeText(this, "setMaxDate", Toast.LENGTH_SHORT).show();


            i++;
          break;
        case 9:
            Toast.makeText(this, "resetCalendar", Toast.LENGTH_SHORT).show();

            mRecyclerCalendarView.resetCalendar();
          i++;
          break;
      }


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
