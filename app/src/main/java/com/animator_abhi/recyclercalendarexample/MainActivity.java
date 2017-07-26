package com.animator_abhi.recyclercalendarexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView;


public class MainActivity extends Activity {
    private RecyclerCalendarView mRecyclerCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerCalendarView = new RecyclerCalendarView(this);
       // mRecyclerCalendarView.setMinDate(2014,1);
      //  mRecyclerCalendarView.setMaxDate(2015,1);
        setContentView(mRecyclerCalendarView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main, menu);

        boolean doubleSelected = mRecyclerCalendarView.isDoubleSelectedMode();
        MenuItem doubleSelectedMenuItem = menu.findItem(R.id.double_selected_mode);
        doubleSelectedMenuItem.setTitle(doubleSelected ? R.string.single_selected_mode
                : R.string.double_selected_mode);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.double_selected_mode: {
                boolean doubleSelectedMode = mRecyclerCalendarView.isDoubleSelectedMode();
                mRecyclerCalendarView.setDoubleSelectedMode(!doubleSelectedMode);
                mRecyclerCalendarView.scrollToSelected();
            //    mRecyclerCalendarView.setBgColor(Color.GREEN);

                item.setTitle(doubleSelectedMode ? R.string.double_selected_mode : R.string.single_selected_mode);

                return true;
            }
            case R.id.reset_selected: {
                mRecyclerCalendarView.resetSelected();

                return true;
            }
            case R.id.scroll_to_today: {
                mRecyclerCalendarView.scrollToToday();

                return true;
            }
            case R.id.scroll_to_selected: {
                mRecyclerCalendarView.scrollToSelected();

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
