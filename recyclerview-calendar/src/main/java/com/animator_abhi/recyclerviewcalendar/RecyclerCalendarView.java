package com.animator_abhi.recyclerviewcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Recycler calendar view.
 */
public class RecyclerCalendarView extends FrameLayout {


    // public static  String selectedDate;
    public int[] selectedDate=new int[3];
    private int[] mTodayDate;

    /*date are in int array of size 3
    index 0 for year
    index 1 for month
    index 2 for day
     */
    static private int minDate[] = new int[3];
    static private int maxDate[] = new int[3];
    /**
     * List of Events contains date in integer array of size 3
     * index 0 for year
     * index 1 for month
     * index 2 for day
     */
    static private List<int[]> events;


    /**
     * List of Disable dates contains date in integer array of size 3
     * index 0 for year
     * index 1 for month
     * index 2 for day
     */
    static private List<int[]> disableDates;

    private FixedHeaderRecyclerView mCalendarRecyclerView;

    private GridLayoutManager mCalendarLayoutManager;

    private CalendarAdapter mCalendarAdapter;

    public RecyclerCalendarView(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    /*set event*/
    public void setEvent(List<int[]> eventDates) {
        this.events = eventDates;
        updateCalendar();
    }

    /*set disable date*/
    public void setDisableDates(List<int[]> disableDates) {
        setDisableDates(disableDates, false);
    }

    /**
     * boolean paramenter if true than event on disable will also be of same color as disable date
     * else its colorful
     */

    public void setDisableDates(List<int[]> disableDates, boolean isEventColorDisable) {
        this.disableDates = disableDates;
        Util.getInstance().setDisableDateEventColor(isEventColorDisable);
        resetSelected();
        updateCalendar();
    }


    /* set min date*/
    public void setMinDate(int minYear, int minMonth) {

        minDate[0] = minYear;
        minDate[1] = minMonth;
        minDate[2] = 1;
        updateCalendar();
    }

    /* set min date*/
    public void setMinDate(int minYear, int minMonth, int minDay) {

        minDate[0] = minYear;
        minDate[1] = minMonth;
        minDate[2] = minDay;
        updateCalendar();
    }

    /* set max date*/
    public void setMaxDate(int maxYear, int maxMonth) {

        maxDate[0] = maxYear;
        maxDate[1] = maxMonth;
        maxDate[2] = 1;
        updateCalendar();
    }

    /* set max date*/
    public void setMaxDate(int maxYear, int maxMonth, int maxDay) {

        maxDate[0] = maxYear;
        maxDate[1] = maxMonth;
        maxDate[2] = maxDay;
        updateCalendar();
    }

    /**
     * set visibility of fixed month header
     */
    public void showMonthHeader(boolean flg) {
        if (flg == true) {
            mCalendarRecyclerView.setFixedHeaderView(R.layout.item_month);
        } else {
            mCalendarRecyclerView.setFixedHeaderView(0);
        }
    }

    /**
     * function to update the calendar data
     * call it whenever selection mode, min date, max date, events or disable date is modified or set
     */
    private void updateCalendar() {
        resetSelected();
        mCalendarAdapter.setCalendarData(CalendarEntity.newCalendarData(mDoubleSelectedMode, mTodayDate, minDate, maxDate, events, disableDates));
    }

    public RecyclerCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RecyclerCalendarView,
                defStyleAttr, 0);
        Util.init(getContext());

        mTodayDate = Util.getTodayDate();

        inflate(getContext(), R.layout.view_recycler_calendar, this);

        mCalendarRecyclerView = (FixedHeaderRecyclerView) findViewById(R.id.calendar);
        // monthTextView.setTextSize(56);
        mCalendarLayoutManager = new GridLayoutManager(getContext(), 7);
        mCalendarRecyclerView.setLayoutManager(mCalendarLayoutManager);


        mCalendarAdapter = new CalendarAdapter(getContext());

        mCalendarAdapter.setOnDayClickListener(new CalendarAdapter.OnDayClickListener() {
            @Override
            void onDayClick(int position) {
                super.onDayClick(position);

                clickPosition(position, true, true);
            }
        });



     /*   mCalendarAdapter.setOnDayLongClickListener(new CalendarAdapter.OnDayLongClickListener() {
                                                       @Override
                                                       void onDayLongClick(int position) {
                                                           super.onDayLongClick(position);
                                                           //  clickPosition(position, true, true);
                                                           Toast.makeText(getContext(), "long click", Toast.LENGTH_SHORT).show();
                                                       }
                                                   }


        );*/
        setSelectedDayBackgroundColor(a.getColor(R.styleable.RecyclerCalendarView_selectionBackgroundColor,getResources().getColor(R.color.background_selected)));
        setDoubleSelectedMode(a.getBoolean(R.styleable.RecyclerCalendarView_doubleSelectedMode,false));
        showMonthHeader(a.getBoolean(R.styleable.RecyclerCalendarView_showFixedHeader,false));
        setMonthDividerVisible(a.getBoolean(R.styleable.RecyclerCalendarView_showMonthDivider,false));
        mCalendarRecyclerView.setAdapter(mCalendarAdapter);
     //  setDecoratorItem(0);
/**
 * by default selection mode is single
 *
 */
        scrollToSelected();
    }

    //*****************************************************************************************************************
    // Select Mode.

    /**
     * if true double selection mode else, single selection  mode.
     */
    private boolean mDoubleSelectedMode;

    /**
     * The currently selected first position.
     */
    private int mSelectedPositionA = -1;
    /**
     * The currently selected second position.
     */
    private int mSelectedPositionB = -1;

    /**
     * return double mode is set or not.
     */
    public boolean isDoubleSelectedMode() {
        return mDoubleSelectedMode;
    }

    /**
     * Set whether dual mode selected and Select the date and reset.
     */
    public void setDoubleSelectedMode(boolean doubleSelectedMode) {
        setDoubleSelectedMode(doubleSelectedMode, true);
    }

    /**
     * set single selected mode and specifies the selected date.
     */
    public void setDoubleSelectedMode(int[] date) {
        setDoubleSelectedMode(false, false);

        clickPosition(getPosition(date), true, false);
    }

    /**
     * Set two-way selection mode and specifies the selected date.
     */
    public void setDoubleSelectedMode(int[] dateFrom, int[] dateTo) {
        setDoubleSelectedMode(true, false);

        clickPosition(getPosition(dateFrom), false, false);
        clickPosition(getPosition(dateTo), true, false);
    }

    /**
     * @param doubleSelectedMode   to set double selected mode
     * @param notifyDataSetChanged to reset the selection
     */
    private void setDoubleSelectedMode(boolean doubleSelectedMode, boolean notifyDataSetChanged) {
        if (mDoubleSelectedMode != doubleSelectedMode) {
            mDoubleSelectedMode = doubleSelectedMode;

            mCalendarAdapter.setCalendarData(null);
        }

        if (mCalendarAdapter.getCalendarData().isEmpty()) {


            mCalendarAdapter.setCalendarData(CalendarEntity.newCalendarData(mDoubleSelectedMode, mTodayDate, minDate, maxDate, events, disableDates));

        }


        resetSelected(notifyDataSetChanged);
    }

    /**
     * Reset selected date.
     */
    public void resetSelected() {
        resetSelected(true);
    }

    private void resetSelected(boolean notifyDataSetChanged) {
        if (mDoubleSelectedMode) {
            unselectPositionAB();
        } else {
            selectPositionB(-1);
            selectPositionA(getPosition(mTodayDate));
        }

        if (notifyDataSetChanged) {
            mCalendarAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Click postion.
     */
    private void clickPosition(int position, boolean notifyDataSetChanged, boolean callback) {
        if (mDoubleSelectedMode) {
            // double mode selection.
            if (mSelectedPositionA == -1) {
                // Two are not selected.
                selectPositionB(-1);
                selectPositionA(position);
                if (callback) {
                    onDoubleFirstSelected(mSelectedPositionA);
                }
            } else if (mSelectedPositionB == -1) {
                // Selected first.
                if (position == mSelectedPositionA) {
                    //To deselect first.
                    selectPositionA(-1);
                    if (callback) {
                        onDoubleFirstUnselected(position);
                    }
                } else {
                    // To select the second.
                    int selectedCount = getPositionABSelectedCount(mSelectedPositionA, position);
                    if (selectedCount <= Util.getInstance().max_double_selected_count) {
                        selectPositionAB(mSelectedPositionA, position);
                        if (callback) {
                            onDoubleSelected(mSelectedPositionA, mSelectedPositionB, selectedCount);
                        }
                    } else {
                        if (callback) {
                            onExceedMaxDoubleSelectedCount(selectedCount);
                        }
                    }
                }
            } else {
                // Two are selected.
                unselectPositionAB();
                selectPositionA(position);
                if (callback) {
                    onDoubleFirstSelected(mSelectedPositionA);
                }
            }
        } else {
            selectPositionA(position);
            if (callback) {
                onSingleSelected(mSelectedPositionA);
            }
        }

        if (notifyDataSetChanged) {
            mCalendarAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Set the first position.
     */
    private void selectPositionA(int position) {
        if (mSelectedPositionA == position) {
            return;
        }

        if (mSelectedPositionA != -1) {
            setPositionSelected(mSelectedPositionA, CalendarEntity.SELECTED_TYPE_UNSELECTED);
            mSelectedPositionA = -1;
        }

        if (position == -1) {
            return;
        }

        setPositionSelected(position, CalendarEntity.SELECTED_TYPE_SELECTED);
        mSelectedPositionA = position;
    }

    /**
     * set second position.
     */
    private void selectPositionB(int position) {
        if (mSelectedPositionB == position) {
            return;
        }

        if (mSelectedPositionB != -1) {
            setPositionSelected(mSelectedPositionB, CalendarEntity.SELECTED_TYPE_UNSELECTED);
            mSelectedPositionB = -1;
        }

        if (position == -1) {
            return;
        }

        setPositionSelected(position, CalendarEntity.SELECTED_TYPE_SELECTED);
        mSelectedPositionB = position;
    }

    /**
     * Return two positions selected number of days.
     */
    private int getPositionABSelectedCount(int positionA, int positionB) {
        if (positionA == -1 || positionB == -1) {
            return 0;
        }

        int fromPosition = Math.min(positionA, positionB);
        int toPosition = Math.max(positionA, positionB);

        int selectedCount = 0;
        for (int i = fromPosition; i <= toPosition; i++) {
            if (mCalendarAdapter.getCalendarEntity(i).itemType == CalendarEntity.ITEM_TYPE_DAY) {
                ++selectedCount;
            }
        }

        return selectedCount;
    }

    /**
     * deselect the double selection
     */
    private void unselectPositionAB() {
        if (mSelectedPositionA != -1 && mSelectedPositionB != -1) {
            for (int i = mSelectedPositionA; i <= mSelectedPositionB; i++) {
                setPositionSelected(i, CalendarEntity.SELECTED_TYPE_UNSELECTED);
            }

            mSelectedPositionA = -1;
            mSelectedPositionB = -1;

            return;
        }

        selectPositionA(-1);
        selectPositionB(-1);
    }

    /**
     * select double position.
     */
    private void selectPositionAB(int positionA, int positionB) {
        if (positionA == -1 || positionB == -1) {
            return;
        }

        int fromPosition = Math.min(positionA, positionB);
        int toPosition = Math.max(positionA, positionB);

        selectPositionA(fromPosition);
        selectPositionB(toPosition);

        for (int i = fromPosition + 1; i < toPosition; i++) {
            setPositionSelected(i, CalendarEntity.SELECTED_TYPE_RANGED);
        }
    }

    /**
     * set postion selected.
     */
    private void setPositionSelected(int position, int selected) {
        CalendarEntity calendarEntity = mCalendarAdapter.getCalendarData().get(position);
        if (calendarEntity.itemType == CalendarEntity.ITEM_TYPE_DAY) {
            calendarEntity.selectedType = selected;
        }
    }

    /**
     * Returns the date of position selected , else -1.
     */
    private int getPosition(int[] date) {
        for (int position = 0; position < mCalendarAdapter.getCalendarData().size(); position++) {
            CalendarEntity calendarEntity = mCalendarAdapter.getCalendarData().get(position);
            if (calendarEntity.itemType == CalendarEntity.ITEM_TYPE_DAY
                    && Util.isDateEqual(calendarEntity.date, date)) {
                return position;
            }
        }

        return -1;
    }

    //*****************************************************************************************************************
    // scroll.

    /**
     * scroll to position
     */
    private int mScrollToPosition = -1;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        scrollToPosition(mScrollToPosition);
    }

    /**
     * scroll to today.
     */
    public void scrollToToday() {
        scrollToPosition(getPosition(mTodayDate));
    }

    /**
     * Scroll to the chosen location or to today
     */
    public void scrollToSelected() {
        if (mDoubleSelectedMode && mSelectedPositionA != -1) {
            if (mSelectedPositionB == -1) {
                scrollToPosition(mSelectedPositionA);
            } else {
                scrollToPosition(Math.min(mSelectedPositionA, mSelectedPositionB));
            }
        } else if (!mDoubleSelectedMode && mSelectedPositionA != -1) {
            scrollToPosition(mSelectedPositionA);
        } else {
            scrollToToday();
        }
    }

    /**
     * Scroll to the specified location, if -1 do nothing.
     */
    private void scrollToPosition(int position) {
        mScrollToPosition = position;

        int calendarRecyclerViewMeasuredHeight = mCalendarRecyclerView.getMeasuredHeight();
        if (mScrollToPosition == -1 || calendarRecyclerViewMeasuredHeight == 0) {
            return;
        }

        int offset = calendarRecyclerViewMeasuredHeight / 2;
        mCalendarLayoutManager.scrollToPositionWithOffset(mScrollToPosition, offset);
        mScrollToPosition = -1;
    }

    //*****************************************************************************************************************
    // Callback.

    /**
     * single selected callback.
     */
    public void onSingleSelected(int position) {
        CalendarEntity calendarEntity = mCalendarAdapter.getCalendarEntity(position);
        Toast.makeText(getContext(), Util.getDateString(calendarEntity.date), Toast.LENGTH_SHORT).show();
        selectedDate = calendarEntity.date;
    }

    /**
     * double mode selected callback.
     */
    private void onDoubleSelected(int positionFrom, int positionTo, int dayCount) {
        CalendarEntity calendarEntityFrom = mCalendarAdapter.getCalendarEntity(positionFrom);
        CalendarEntity calendarEntityTo = mCalendarAdapter.getCalendarEntity(positionTo);
        Toast.makeText(getContext(), Util.getDateString(calendarEntityFrom.date) + "~" +
                Util.getDateString(calendarEntityTo.date) + "," + dayCount, Toast.LENGTH_SHORT).show();
    }

    /**
     * Double mode With selected first  date callback.
     */
    private void onDoubleFirstSelected(int position) {
        CalendarEntity calendarEntity = mCalendarAdapter.getCalendarEntity(position);
        Toast.makeText(getContext(), "First select:" + Util.getDateString(calendarEntity.date), Toast.LENGTH_SHORT).show();
    }

    /**
     * Double mode With De-selected first  date callback
     */
    private void onDoubleFirstUnselected(int position) {
        CalendarEntity calendarEntity = mCalendarAdapter.getCalendarEntity(position);
        Toast.makeText(getContext(), "First Deselect:" + Util.getDateString(calendarEntity.date), Toast.LENGTH_SHORT).show();
    }

    /**
     * double selection count exceed max count.
     */
    private void onExceedMaxDoubleSelectedCount(int dayCount) {
        Toast.makeText(getContext(), "" + dayCount, Toast.LENGTH_SHORT).show();
    }

    //*****************************************************************************************************************
    // Methods of RecyclerView Calendar

    /*set calendar background color
    */
    public void setBgColor(int color) {
        mCalendarRecyclerView.setBackgroundColor(color);
    }


    //*****************************************************************************************************************
    //****************************************************************************************************************
    // Methods for Fixed Month Header.
    /*
    return fixedFeaderTextView

    */
    public TextView getFixedHeaderTextView()

    {
        return mCalendarRecyclerView.getFixedHeader();

    }


    //*********************************************


    @IntDef({
            TEXT_ALIGNMENT_CENTER,
            TEXT_ALIGNMENT_TEXT_START,
            TEXT_ALIGNMENT_TEXT_END,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextAlignment {
    }


    /**
     * Align to the start of the paragraph, e.g. ALIGN_NORMAL.
     * <p>
     * Use with {@link #alignFixedHeaderTextView(int)}
     */
    public static final int TEXT_ALIGNMENT_TEXT_START = 0;

    /**
     * Center the paragraph, e.g. ALIGN_CENTER.
     * <p>
     * Use with {@link #alignFixedHeaderTextView(int)}
     */
    public static final int TEXT_ALIGNMENT_CENTER = 1;

    /**
     * Align to the end of the paragraph, e.g. ALIGN_OPPOSITE.
     * <p>
     * Use with {@link #alignFixedHeaderTextView(int)}
     */
    public static final int TEXT_ALIGNMENT_TEXT_END = 2;


//align fixedHeaderTextView

    public void alignFixedHeaderTextView(@TextAlignment int pos) {

 if(getFixedHeaderView()!=null)
        switch (pos) {
            case TEXT_ALIGNMENT_TEXT_START:
                getFixedHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                break;
            case TEXT_ALIGNMENT_CENTER:
                getFixedHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                break;
            case TEXT_ALIGNMENT_TEXT_END:
                getFixedHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                break;
            default:
                getFixedHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        }
    }


    //******************************************************
// return FixedHeaderView
    public View getFixedHeaderView() {
        return mCalendarRecyclerView.getFixedHeaderView();
    }

 /*   public void setHeaderTextSize(float size)
    {
      //  mCalendarRecyclerView.setHeaderSize(size);
        requestLayout();
    }*/

    public void setFixedHeaderColor(int color) {
        mCalendarRecyclerView.setFixedHeaderColor(color);

    }

    //*****************************************************************************************************************

    public void setMonthTextAlignment(@TextAlignment int val) {
        Util.getInstance().monthAlignment = val;
    }

    public void setMonthPadding(int left, int top, int right, int bottom) {
        Util.getInstance().monthPadding[0] = left;
        Util.getInstance().monthPadding[1] = top;
        Util.getInstance().monthPadding[2] = right;
        Util.getInstance().monthPadding[3] = bottom;
    }

    public int[] getTodayDate() {
        return mTodayDate;
    }

    public int[] getSelectedDate() {
        return selectedDate;
    }

    public void setWeekendDayColor(int color) {
        Util.getInstance().setText_weekend(color);
        requestLayout();

    }

    /**
     * set event day color
     *
     * @param color
     */
    public void setEventColor(int color) {
        Util.getInstance().setText_special(color);
        requestLayout();

    }


    /**
     * set selection day color
     *
     * @param color
     */
    public void setSelectionDayColor(int color) {
        Util.getInstance().setText_selected(color);
        requestLayout();

    }

    /*  public void setBackgroundDayColor(int color)
      {
          Util.getInstance().setBackground_day(color);
          requestLayout();
      }*/

    /**
     * set all days Text color
     *
     * @param color
     */
    public void setDayColor(int color) {
        Util.getInstance().setText_day(color);
        requestLayout();
    }

    /**
     * set range decorator color
     *
     * @param color
     */
    public void setBackgroundRangeColor(int color) {
        Util.getInstance().setBackground_ranged(color);
        requestLayout();
    }

    /**
     * set selected day decorator color
     *
     * @param color
     */
    public void setSelectedDayBackgroundColor(int color) {
        Util.getInstance().setBackground_selected(color);
        requestLayout();
    }

    /**
     * set disable days text color
     *
     * @param color
     */
    public void setDisableDayColor(int color) {
        Util.getInstance().setText_disabled(color);
        requestLayout();
    }

    /**
     * set today text color
     *
     * @param color
     */
    public void setTodayColor(int color) {
        Util.getInstance().setText_today(color);
        requestLayout();
    }

    /**
     * set decorator
     *
     * @param decorator
     */

    /********************************************************************************************************/
    // DECORATOR
    public static final int SIMPLE_DECORATOR =0;
    public static final int SIMPLE_OUTLINE_DECORATOR=1;
    public static final int DESIGNER_DECORATOR=2;

    @IntDef({
            SIMPLE_DECORATOR,
            SIMPLE_OUTLINE_DECORATOR,
            DESIGNER_DECORATOR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DayDecorator {
    }

    public void setPresetDecoratorItem(@DayDecorator int decorator) {
        switch(decorator)
        {
            case SIMPLE_DECORATOR:
               setDecoratorItem(R.drawable.selected_day_simple_decorator);
                break;
            case SIMPLE_OUTLINE_DECORATOR:
                setDecoratorItem(R.drawable.selected_day_outline_decorator);
                break;
            case DESIGNER_DECORATOR:
                setDecoratorItem(R.drawable.selected_day_designer_decorator);
                break;
        }

        requestLayout();
    }



    public void setDecoratorItem(int decorator) {
        Util.getInstance().setDecorator(decorator);
        requestLayout();
    }


    /**************************************************************************************************************/
    /**
     * resent calendar data
     */
    public void resetCalendar() {
        minDate[0] = 0;
        minDate[2] = 0;
        minDate[1] = 0;
        maxDate[0] = 0;
        maxDate[1] = 0;
        maxDate[2] = 0;
        if (disableDates != null) {
            disableDates.clear();
        }
        if (events != null) {
            events.clear();
        }

        Util.getInstance().resetUtil(getContext());
        updateCalendar();
    }

    /**
     * set month divider visibility
     *
     * @param val
     */
    public void setMonthDividerVisible(boolean val) {
        Util.getInstance().setDividerVisibility(val);
    }

    /**
     * set divider color
     *
     * @param color
     */
    public void setDividerColor(int color) {
        setDividerColor(color, false, true, false);
    }

    /**
     * if flag vale true than color is applied to that divider
     *
     * @param color  set divider color
     * @param top    flg to set top divider color
     * @param middle flg to set middle divider color
     * @param bottom flg to set bottom divider color
     */
    public void setDividerColor(int color, boolean top, boolean middle, boolean bottom) {
        boolean[] b = new boolean[3];
        b[0] = top;
        b[1] = middle;
        b[2] = bottom;

        Util.getInstance().setDividerColor(color, b);
    }


    /*********************************************************************************************
     /***
     *
     * @param size set MONTH_TEXTVIEW_ATTRIBUTES
     */
    public void setMonthTextViewSize(float size) {
        Util.getInstance().setMonthSize(size);
        updateCalendar();
    }

    public void setMonthTextColor(int color)
    {
        Util.getInstance().monthColor=color;
    }


    public void setMonthBackgroundColor(int color) {
        Util.getInstance().monthBackgroundColor=color;
    }


}
