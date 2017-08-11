package com.animator_abhi.recyclerviewcalendar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter.
 */
final class CalendarAdapter extends RecyclerView.Adapter implements FixedHeaderRecyclerView.FixedHeaderAdapter {

    private final String[] monthName = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final LayoutInflater mLayoutInflater;

    private final List<CalendarEntity> mCalendarData = new ArrayList<>();

    private OnDayClickListener mOnDayClickListener;
    private OnDayLongClickListener mOnDayLongClickListener;

    CalendarAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    public List<CalendarEntity> getCalendarData() {
        return mCalendarData;
    }


    public void setCalendarData(List<CalendarEntity> calendarData) {
        mCalendarData.clear();

        if (calendarData != null) {
            mCalendarData.addAll(calendarData);
        }
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mOnDayClickListener = onDayClickListener;
    }

    public void setOnDayLongClickListener(OnDayLongClickListener onDayLongClickListener) {
        mOnDayLongClickListener = onDayLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CalendarEntity.ITEM_TYPE_MONTH: {
                return new MonthViewHolder(mLayoutInflater.inflate(R.layout.item_month, parent, false));
            }
            case CalendarEntity.ITEM_TYPE_DAY: {
                return new DayViewHolder(mLayoutInflater.inflate(R.layout.item_day, parent, false));
            }
            case CalendarEntity.ITEM_TYPE_EMPTY_DAY: {
                return new EmptyDayViewHolder(mLayoutInflater.inflate(R.layout.item_empty_day, parent, false));
            }
            case CalendarEntity.ITEM_TYPE_DIVIDER: {
                return new DividerViewHolder(mLayoutInflater.inflate(R.layout.item_divider, parent, false));
            }
            default: {
                return null;
            }
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        final int layoutPosition = holder.getLayoutPosition();
        final CalendarEntity calendarEntity = getCalendarEntity(holder.getLayoutPosition());

        switch (viewType) {
            case CalendarEntity.ITEM_TYPE_MONTH: {
                MonthViewHolder monthViewHolder = (MonthViewHolder) holder;
                //   String month=calendarEntity.monthString;
                //String year=calendarEntity.monthString;
                monthViewHolder.monthTextView.setText(monthName[calendarEntity.date[1] - 1] + " " + calendarEntity.date[0]);
                if (Util.getInstance().isDividerVisible) {
                    monthViewHolder.divider.setVisibility(View.VISIBLE);
                } else {
                    monthViewHolder.divider.setVisibility(View.INVISIBLE);
                }

                //  monthViewHolder.monthTextView.setTextSize(54);
                //   Log.d("month", ""+calendarEntity.date[1]);
                break;
            }
            case CalendarEntity.ITEM_TYPE_DAY: {
                DayViewHolder dayViewHolder = (DayViewHolder) holder;

                dayViewHolder.itemView.setEnabled(calendarEntity.isEnabled);
                /*
                * switch between setBackgroundColor/setBackground/setBackgroundResource according to need and change return value of getBackgroundColor() accordingly */
                // dayViewHolder.itemView.setBackgroundColor(calendarEntity.getBackgroundColor());
                //  dayViewHolder.itemView.setBackground(generateCircleDrawable(calendarEntity.getBackgroundColor()));
                dayViewHolder.itemView.setBackgroundResource(calendarEntity.getBackgroundColor());

                if (dayViewHolder.itemView != null) {
                    try {
                        GradientDrawable gd = (GradientDrawable) dayViewHolder.itemView.getBackground().getCurrent();
                        gd.setColor(Util.getInstance().background_selected);
                    } catch (Exception e) {
                    }

                }

                dayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnDayClickListener != null && calendarEntity.isEnabled) {
                            mOnDayClickListener.onDayClick(layoutPosition);

                        }
                    }
                });

                dayViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        if (mOnDayLongClickListener != null && calendarEntity.isEnabled) {
                            mOnDayLongClickListener.onDayLongClick(layoutPosition);

                        }
                        return false;
                    }
                });

                dayViewHolder.dayTextView.setText(calendarEntity.dayString);
                dayViewHolder.dayTextView.setTextColor(calendarEntity.getTextColor());
                dayViewHolder.specialTextView.setVisibility(View.GONE);
                if (calendarEntity.specialString == null) {
                    dayViewHolder.specialTextView.setVisibility(View.GONE);
                } else {
                   if(calendarEntity.isEnabled)
                   {
                       dayViewHolder.dayTextView.setTextColor(Util.getInstance().text_day);

                       if (calendarEntity.isWeekend)
                       {
                           dayViewHolder.dayTextView.setTextColor(Util.getInstance().text_weekend);
                       }
                       if (calendarEntity.selectedType!=calendarEntity.SELECTED_TYPE_UNSELECTED)
                       {
                           dayViewHolder.dayTextView.setTextColor(Util.getInstance().text_selected);
                       }
                   }
                    dayViewHolder.specialTextView.setVisibility(View.VISIBLE);
                    dayViewHolder.specialTextView.setTextColor(Util.getInstance().text_special);
                    // dayViewHolder.specialTextView.setText("\u2022");
                }
                if (Util.getInstance().isEventColorDisable) {
                    dayViewHolder.specialTextView.setTextColor(calendarEntity.getTextColor());
                } else {
                    dayViewHolder.specialTextView.setTextColor(Util.getInstance().text_special);
                }


                //  dayViewHolder.specialTextView.setTextColor(Color.CYAN);

                break;

            }
            case CalendarEntity.ITEM_TYPE_DIVIDER:
                DividerViewHolder dividerViewHolder = (DividerViewHolder) holder;
                if (Util.getInstance().isDividerVisible) {
                    dividerViewHolder.divider.setVisibility(View.VISIBLE);
                } else {
                    dividerViewHolder.divider.setVisibility(View.INVISIBLE);
                }

                break;
        }
    }


    private static Drawable generateCircleDrawable(final int color) {
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(color);
        return drawable;
    }


    @Override
    public int getItemCount() {
        return mCalendarData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
        final int spanCount = gridLayoutManager.getSpanCount();

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemType = getItemViewType(position);
                if (itemType == CalendarEntity.ITEM_TYPE_MONTH || itemType == CalendarEntity.ITEM_TYPE_DIVIDER) {
                    return spanCount;
                }
                if (oldSizeLookup != null) {
                    return oldSizeLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return getCalendarEntity(position).itemType;
    }

    public CalendarEntity getCalendarEntity(int position) {
        return mCalendarData.get(position);
    }

    @Override
    public int getFixedHeaderState(int position) {
        return getCalendarEntity(position).isLastSundayOfMonth
                ? FixedHeaderRecyclerView.FixedHeaderAdapter.STATE_PUSHABLE
                : FixedHeaderRecyclerView.FixedHeaderAdapter.STATE_VISIBLE;
    }

    @Override
    public void configureFixedHeader(View FixedHeaderView, int position) {
        TextView yearMonthTextView = (TextView) FixedHeaderView.findViewById(R.id.month);
        //  yearMonthTextView.setText(getCalendarEntity(position).monthString);

        yearMonthTextView.setText(monthName[getCalendarEntity(position).date[1] - 1] + " " + getCalendarEntity(position).date[0]);
        Log.d("CalendarAdapter", "" + getCalendarEntity(position).date[1]);
        //  monthName[calendarEntity.date[1]-1]+" "+calendarEntity.date[0]
    }

    //*****************************************************************************************************************
    // Listener.

    static abstract class OnDayClickListener {
        void onDayClick(int position) {
        }
    }

    static abstract class OnDayLongClickListener {

        void onDayLongClick(int position) {
        }
    }

    //*****************************************************************************************************************
    // ViewHolder.

    private static final class MonthViewHolder extends RecyclerView.ViewHolder {
        public final TextView monthTextView;
        public final View divider;


        MonthViewHolder(View itemView) {
            super(itemView);

            monthTextView = (TextView) itemView.findViewById(R.id.month);
            divider = itemView.findViewById(R.id.divider);

            if (Util.getInstance().isDividerColorChangeAt[1]) {
                divider.setBackgroundColor(Util.getInstance().dividerColor);
            }
            monthTextView.setTextColor(Util.getInstance().monthColor);

            monthTextView.setBackgroundColor(Util.getInstance().monthBackgroundColor);

            monthTextView.setTextSize(Util.getInstance().monthTextSize);
          switch (Util.getInstance().monthAlignment)
          {case 0:
              monthTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
          break;
              case 1:
                  monthTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                  break;

              case 2:
                  monthTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                  break;

              default:

                  monthTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

          }

          monthTextView.setPadding(Util.getInstance().monthPadding[0],Util.getInstance().monthPadding[1],Util.getInstance().monthPadding[2],Util.getInstance().monthPadding[3]);



        }
    }

    private static final class DayViewHolder extends RecyclerView.ViewHolder {
        public final TextView dayTextView;
        public final TextView specialTextView;

        DayViewHolder(View itemView) {
            super(itemView);

            dayTextView = (TextView) itemView.findViewById(R.id.day);
            specialTextView = (TextView) itemView.findViewById(R.id.special);

        }
    }

    private static final class EmptyDayViewHolder extends RecyclerView.ViewHolder {
        EmptyDayViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static final class DividerViewHolder extends RecyclerView.ViewHolder {
        public final View divider;

        DividerViewHolder(View itemView) {
            super(itemView);
            divider = itemView.findViewById(R.id.divider);
            if (Util.getInstance().isDividerColorChangeAt[2]) {
                divider.setBackgroundColor(Util.getInstance().dividerColor);
            }

            //  divider.setBackgroundColor(Color.RED);
        }
    }


}
