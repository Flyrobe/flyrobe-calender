package com.animator_abhi.recyclerviewcalendar.recycleCal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.animator_abhi.recyclerviewcalendar.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ANIMATOR ABHI on 19/07/2017.
 */

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.MyViewHolder> {

List<CalendarEntity> data= Collections.emptyList();
    private  LayoutInflater inflator;

    CalAdapter(Context context,List<CalendarEntity> data)
    {
       inflator= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view=inflator.inflate(R.layout.custom_cal_view,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CalendarEntity current=data.get(position);
        holder.tv.setText(current.title);
        holder.b.setText(current.btnData);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

   class MyViewHolder extends RecyclerView.ViewHolder{
       Button b;
       TextView tv;
       public MyViewHolder(View itemView) {
           super(itemView);
           b= (Button) itemView.findViewById(R.id.button1);
           tv= (TextView) itemView.findViewById(R.id.textView1);
       }
   }
}
