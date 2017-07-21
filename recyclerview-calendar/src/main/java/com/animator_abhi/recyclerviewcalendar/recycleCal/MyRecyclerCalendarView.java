package com.animator_abhi.recyclerviewcalendar.recycleCal;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.animator_abhi.recyclerviewcalendar.PinnedHeaderRecyclerView;
import com.animator_abhi.recyclerviewcalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANIMATOR ABHI on 19/07/2017.
 */

public class MyRecyclerCalendarView extends FrameLayout {

    private CalAdapter calAdapter;
    private RecyclerView  recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager mCalendarLayoutManager;
    private PinnedHeaderRecyclerView mCalendarRecyclerView;


    public MyRecyclerCalendarView(@NonNull Context context) {
        this(context, null);
    }

    public MyRecyclerCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.activity_main_custom,this);
       // mCalendarRecyclerView = (PinnedHeaderRecyclerView) findViewById(R.id.calendar);

        recyclerView= (RecyclerView) findViewById(R.id.rv);
       calAdapter=new CalAdapter(getContext(),getData());
     //   LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mCalendarLayoutManager=new GridLayoutManager(getContext(),7);
        layoutManager=new RecyclerView.LayoutManager() {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
      recyclerView.setLayoutManager(mCalendarLayoutManager);
       recyclerView.setAdapter(calAdapter);
       // mCalendarRecyclerView.setAdapter(calAdapter);

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
