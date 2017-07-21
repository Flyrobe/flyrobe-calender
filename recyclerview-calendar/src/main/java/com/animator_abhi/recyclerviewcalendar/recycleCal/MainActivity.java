package com.animator_abhi.recyclerviewcalendar.recycleCal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.animator_abhi.recyclerviewcalendar.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   RecyclerView recyclerView;
    CalAdapter calAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom);

    }


    public static List<CalendarEntity> getData()
    {
        List<CalendarEntity> data=new ArrayList<>();
        String title[]={"first","second","third","first4","second","third","first","second","third"};
        String btnData[]={"b1","b2","b3","b14","b2","b3","b1","b2","b3"};

        for(int i=0;i<title.length;i++)
        {
            CalendarEntity current=new CalendarEntity();
            current.title=title[i];
            current.btnData=btnData[i];
            data.add(current);
        }

        return data;
    }
}
