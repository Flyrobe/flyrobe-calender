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
    static int i = 0;
    //  String min,max;
    //EditText mind,maxd;
    List<int[]> specialEvents;
    List<int[]> disableDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cal);
        todayDate = (TextView) findViewById(R.id.date1);
        todayDate.setTextSize(24);
        todayDate.setVisibility(View.GONE);
        mRecyclerCalendarView = (RecyclerCalendarView) findViewById(R.id.recyclerCalendarView);


        disableDates = new ArrayList<>();
        specialEvents = new ArrayList<>();
        int[] eDates = {2017, 8, 2};
        int[] eDates1 = {2017, 8, 23};
        int[] eDates2 = {2017, 9, 1};
        int[] eDates3 = {2017, 8, 14};
        specialEvents.add(eDates);
        specialEvents.add(eDates1);
        specialEvents.add(eDates2);
        specialEvents.add(eDates3);
        int[] eDates4 = {2018, 1, 2};
        int[] eDates5 = {2018, 1, 23};
        int[] eDates6 = {2018, 1, 1};
        int[] eDates7 = {2018, 1, 14};
        int[] eDates8 = {2018, 2, 1};
        specialEvents.add(eDates4);
        specialEvents.add(eDates5);
        specialEvents.add(eDates6);
        specialEvents.add(eDates7);
        specialEvents.add(eDates8);
        mRecyclerCalendarView.setEvent(specialEvents);



        //  todayDate.setText(""+mRecyclerCalendarView.getTodayDate()[0]+" today is"+mRecyclerCalendarView.getSelectedDate());
        //    mRecyclerCalendarView.setMonthTextViewSize(24);
        //  mRecyclerCalendarView.getFixedHeaderTextView().setTextSize(24);

        todayDate.setText("" + mRecyclerCalendarView.getTodayDate()[0]);
        mRecyclerCalendarView.setMinDate(2017, 8, 8);
        mRecyclerCalendarView.setMaxDate(2018, 2, 15);

        //mRecyclerCalendarView.onSingleSelected(56);
        //   mRecyclerCalendarView.setBgColor(Color.RED);


        int[] dDates = {2017, 8, 20};
        int[] dDates1 = {2017, 8, 21};
        int[] dDates2 = {2017, 9, 10};
        int[] dDates3 = {2017, 10, 9};
        disableDates.add(dDates);
        disableDates.add(dDates1);
        disableDates.add(dDates2);
        int[] dDates4 = {2017, 9, 13};
        int[] dDates5 = {2017, 9, 14};
        int[] dDates6 = {2017, 9, 15};
        int[] dDates7 = {2018, 2, 1};
        int[] dDates8 = {2018, 2, 2};
        disableDates.add(dDates);
        disableDates.add(dDates1);
        disableDates.add(dDates2);
        disableDates.add(dDates3);
        disableDates.add(dDates4);
        disableDates.add(dDates5);
        disableDates.add(dDates6);
        disableDates.add(dDates7);
        disableDates.add(dDates8);
        mRecyclerCalendarView.setDisableDates(disableDates);
        Log.d("block dates", "" + disableDates);
//        mRecyclerCalendarView.setSelectionDayColor(Color.WHITE);
//        mRecyclerCalendarView.setWeekendDayColor(getResources().getColor(R.color.primary_darker_blue));
//        mRecyclerCalendarView.setEventColor(getResources().getColor(R.color.saved_event_selector_color));

        mRecyclerCalendarView.setDoubleSelectedMode(false);
        //mRecyclerCalendarView.setTodayColor(getResources().getColor(R.color.colorAccent));
        mRecyclerCalendarView.showMonthHeader(true);
       // mRecyclerCalendarView.getFixedHeaderTextView().setPadding(64,0,64,0);

      //  mRecyclerCalendarView.getFixedHeaderTextView().setBackgroundColor(getResources().getColor(R.color.saved_event_selector_color));
      //  mRecyclerCalendarView.alignFixedHeaderTextView(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END);
       // mRecyclerCalendarView.setMonthPadding(64,0,64,0);
      //  mRecyclerCalendarView.setMonthTextAlignment(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END);
     //   mRecyclerCalendarView.setMonthBackgroundColor(getResources().getColor(R.color.colorAccent));

     //   mRecyclerCalendarView.setMonthTextColor(Color.WHITE);
        mRecyclerCalendarView.setPresetDecoratorItem(RecyclerCalendarView.DESIGNER_DECORATOR);
       // mRecyclerCalendarView.setDecoratorItem(R.color.saved_event_selector_color);
       // mRecyclerCalendarView.setSelectedDayBackgroundColor(Color.BLACK);
//        mRecyclerCalendarView.setTodayColor(Color.GREEN);
//        mRecyclerCalendarView.setEventColor(Color.GREEN);
//        mRecyclerCalendarView.setDisableDayColor(Color.RED);
//        mRecyclerCalendarView.setWeekendDayColor(Color.BLUE);
        

     //   mRecyclerCalendarView.setDividerColor(Color.BLACK,true,true,true);


        // mRecyclerCalendarView.getFixedHeaderView().setBackgroundColor(Color.RED);
        // mRecyclerCalendarView.getFixedHeaderView().getRootView().setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        //  mRecyclerCalendarView.getFixedHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        //  mRecyclerCalendarView.getFixedHeaderTextView().setPadding(50,0,0,0);
        // mRecyclerCalendarView.alignFixedHeaderTextView(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_START);
        //   mRecyclerCalendarView.setMonthDividerVisible(true);

        // mRecyclerCalendarView.setDecoratorItem(R.drawable.current_day_selector);
        //mRecyclerCalendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.primary_darker_blue));
        // mRecyclerCalendarView.setPinnedHeaderColor(getResources().getColor(R.color.text_today));
    }


    public void change(View v) { // todayDate.setText(""+mRecyclerCalendarView.getSelectedDate()[2]);

        todayDate.setText(mRecyclerCalendarView.getSelectedDate()[2] + "");
        //  mRecyclerCalendarView.setDecoratorItem(R.drawable.ic_my_selector);
        switch (i) {
            case 0:
                mRecyclerCalendarView.setTodayColor(Color.RED);
                // mRecyclerCalendarView.setMonthDividerVisible(true);
                //  mRecyclerCalendarView.setBgColor(Color.GREEN);
//                mRecyclerCalendarView.setMonthPadding(60,0,0,0);
//                //mRecyclerCalendarView.setDecoratorItem(R.drawable.ic_my_selector);
//                mRecyclerCalendarView.setMonthDividerVisible(true);
                mRecyclerCalendarView.setMonthTextAlignment(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END);
//                mRecyclerCalendarView.setMonthTextColor(getResources().getColor(R.color.primary_darker_blue));
//                mRecyclerCalendarView.setMonthBackgroundColor(Color.RED);
//                mRecyclerCalendarView.setMonthTextViewSize(28);
//                mRecyclerCalendarView.setPresetDecoratorItem(RecyclerCalendarView.DESIGNER_DECORATOR);
                // mRecyclerCalendarView.getFixedHeaderTextView().setBackgroundColor(Color.YELLOW);
//mRecyclerCalendarView.resetCalendar();
                //   mRecyclerCalendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.primary_darker_blue));

                // mRecyclerCalendarView.setWeekendDayColor(getResources().getColor(R.color.saved_event_selector_color));
                Toast.makeText(this, "weekend color and background_day_color", Toast.LENGTH_SHORT).show();
                i++;
                break;
            case 1:
                mRecyclerCalendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.primary_darker_blue));
                mRecyclerCalendarView.setMonthTextViewSize(54);

//                 mRecyclerCalendarView.getFixedHeaderView().setBackgroundColor(Color.RED);
//         mRecyclerCalendarView.setEventColor(Color.BLUE);
//          mRecyclerCalendarView.setMonthDividerVisible(true);
//           Toast.makeText(this, "setEventColor", Toast.LENGTH_SHORT).show();
//            mRecyclerCalendarView.getFixedHeaderTextView().setTextColor(Color.GREEN);
//           mRecyclerCalendarView.setDividerColor(Color.RED);
//                mRecyclerCalendarView.setMonthTextAlignment(0);
                i++;
                break;
            case 2:

                //mRecyclerCalendarView.setDecoratorItem(R.color.selected_day_color);
//           Toast.makeText(this, "setSelectionDayColor", Toast.LENGTH_SHORT).show();
//             mRecyclerCalendarView.setMonthPadding(54,0,0,0);
//                // mRecyclerCalendarView.setBackgroundDayColor(Color.CYAN);//
//          mRecyclerCalendarView.setSelectionDayColor(Color.BLACK);
//          mRecyclerCalendarView.setDividerColor(getResources().getColor(R.color.primary_darker_blue),true,true,true);
                i++;
                break;
            case 3:
                mRecyclerCalendarView.setFixedHeaderColor(Color.RED);
                mRecyclerCalendarView.setDayColor(Color.YELLOW);
                Toast.makeText(this, "setDayColor", Toast.LENGTH_SHORT).show();
                mRecyclerCalendarView.setDisableDates(disableDates);
                mRecyclerCalendarView.setMonthTextViewSize(42);
                mRecyclerCalendarView.showMonthHeader(true);
                i++;

                break;
            case 4:
                mRecyclerCalendarView.setDisableDates(disableDates);
                Toast.makeText(this, "setDisableDates", Toast.LENGTH_SHORT).show();
//   mRecyclerCalendarView.getPinnedHeaderView().setForegroundGravity(0);
                //  mRecyclerCalendarView.resetCalendar();
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
                mRecyclerCalendarView.setMinDate(2017, 8, 3);
                Toast.makeText(this, "setMinDate", Toast.LENGTH_SHORT).show();

                i++;
                break;
            case 8:
//            mRecyclerCalendarView.setMaxDate(2017,11,28);
//            Toast.makeText(this, "setMaxDate", Toast.LENGTH_SHORT).show();


                i++;
                break;
            case 9:
//            Toast.makeText(this, "resetCalendar", Toast.LENGTH_SHORT).show();
//
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
