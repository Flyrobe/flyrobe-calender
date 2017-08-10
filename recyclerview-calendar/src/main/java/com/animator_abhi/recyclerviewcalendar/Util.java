package com.animator_abhi.recyclerviewcalendar;

import android.content.Context;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


final class Util {

    private static final int[] DAYS_OF_MONTHS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 1970-1-1.
     */
    private static final int WEEK_OF_19700101 = 4;



    /**
     * return weeks.
     */
    public static int getWeek(int[] date) {
        return (getDaysFrom19700101(date) + WEEK_OF_19700101) % 7;
    }

    /**
     * Return days from 1970-1-1.
     */
    private static int getDaysFrom19700101(int[] date) {
        int days = 0;

        for (int i = 1970; i < date[0]; i++) {
            days += getDaysOfYear(i);
        }

        for (int i = 1; i < date[1]; i++) {
            days += getDaysOfMonth(date[0], i);
        }

        days += date[2] - 1;

        return days;
    }


    private static int getDaysOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    /**
     * return days in month.
     */
    public static int getDaysOfMonth(int year, int month) {
        return month == 2 && isLeapYear(year) ? 29 : DAYS_OF_MONTHS[month - 1];
    }

    /**
     * check for leap year.
     */
    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    /**
     * get today date.
     */
    public static int[] getTodayDate() {
        Calendar calendar = Calendar.getInstance();

        int[] todayDate = new int[3];
        todayDate[0] = calendar.get(Calendar.YEAR);
        todayDate[1] = calendar.get(Calendar.MONTH) + 1;
        todayDate[2] = calendar.get(Calendar.DATE);

        return todayDate;
    }

    /**
     * calculation date.
     */
    public static int[] addDate(int[] date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date[0], date[1] - 1, date[2]);
        calendar.add(Calendar.DATE, add);

        int[] newDate = new int[3];
        newDate[0] = calendar.get(Calendar.YEAR);
        newDate[1] = calendar.get(Calendar.MONTH) + 1;
        newDate[2] = calendar.get(Calendar.DATE);

        return newDate;
    }

    /**
     * computing week.
     */
    public static int addWeek(int week, int add) {
        return (week + add) % 7;
    }

    /**
     * checking date whether its after a certain date.
     * if parameter boolean false,than current day is also consider in checking
     * pass argument false if you want current day also to be consider in isDateBefore/After
     */
    public static boolean isDateAfter(int[] thisDate, int[] date, boolean canEqual) {
        return thisDate[0] > date[0]
                || thisDate[0] == date[0] && thisDate[1] > date[1]
                || thisDate[0] == date[0] && thisDate[1] == date[1]
                && (canEqual ? thisDate[2] >= date[2] : thisDate[2] > date[2]);
    }

    /**
     *  /**
     * checking date whether its before a certain date.
     * if parameter boolean false,than current day is also consider in checking
     * pass argument false if you want current day also to be consider in isDateBefore/After
     */


    public static boolean isDateBefore(int[] thisDate, int[] date, boolean canEqual) {
        return thisDate[0] < date[0]
                || thisDate[0] == date[0] && thisDate[1] < date[1]
                || thisDate[0] == date[0] && thisDate[1] == date[1]
                && (canEqual ? thisDate[2] <= date[2] : thisDate[2] < date[2]);
    }

    /**
     * check whether date lies in between.
     */
    public static boolean isDateBetween(int[] thisDate, int[] dateAfter, int[] dateBefore, boolean canEqualAfter,
                                        boolean canEqualBefore) {
        return isDateAfter(thisDate, dateAfter, canEqualAfter) && isDateBefore(thisDate, dateBefore, canEqualBefore);
    }

    /**
     * check date equal.
     */
    public static boolean isDateEqual(int[] thisDate, int[] date) {
        return thisDate[0] == date[0] && thisDate[1] == date[1] && thisDate[2] == date[2];
    }


    public static boolean isDateEqual(final int[] thisDate, final List<int[]> date) {
        if (date == null) {
            return false;
        } else {
            for (final int[] item : date) {
                if (Arrays.equals(item, thisDate)) {
                    return true;
                }
            }
            return false;
        }

    }

  /*  public static boolean isInList(
            final List<int[]> list, final int[] candidate) {

        return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
        //  ^-- or you may want to use .parallelStream() here instead
    }*/

    /**
     * return last sunday of month.
     */
    public static int getLastSundayOfMonth(int daysOfMonth, int weekOfFirstDayOfMonth) {
        return (daysOfMonth + weekOfFirstDayOfMonth - 1) / 7 * 7 - weekOfFirstDayOfMonth + 1;
    }

    /**
     * convert date in date string.
     */
    public static String getDateString(int[] date) {
        return String.format(getInstance().format_date, date[0], date[1], date[2]);
    }

    //*****************************************************************************************************************
    // Cache.

    private static Util sInstance;

    public static void init(Context context) {
        if (sInstance == null) {
            sInstance = new Util(context);
        }
    }

    public static Util getInstance() {
        return sInstance;
    }

    public int transparent;
    public int background_day;
    public int background_selected;
    public int background_ranged;
    public int background_disabled;
    public int text_day;
    public int text_selected;
    public int text_today;
    public int text_special;
    public int text_weekend;
    public int text_disabled;
    public int dividerColor;
    public float monthTextSize;
    public boolean[] isDividerColorChangeAt = new boolean[3];
    public boolean isEventColorDisable;
    public int decorator;
    public int monthAlignment;
    public int monthPadding[]={0,0,0,0};;
    public int monthColor;
    public int monthBackgroundColor;

    public final int year_from;
    public final int month_from;
    public final int special_count;
    public final int max_double_selected_count;


    public final String special;
    public final String today;
    public final String format_month;
    public final String format_date;

    public static boolean isDividerVisible;


    private Util(Context context) {
        transparent = context.getResources().getColor(R.color.transparent);
        background_day = context.getResources().getColor(R.color.background_day);
        background_selected = context.getResources().getColor(R.color.background_selected);
        background_ranged = context.getResources().getColor(R.color.background_ranged);
        background_disabled = context.getResources().getColor(R.color.background_disabled);
        text_day = context.getResources().getColor(R.color.text_day);
        text_selected = context.getResources().getColor(R.color.text_selected);
        text_today = context.getResources().getColor(R.color.text_today);
        text_special = context.getResources().getColor(R.color.text_special);
        text_weekend = context.getResources().getColor(R.color.text_day);
        dividerColor = context.getResources().getColor(R.color.background_divider);
        text_disabled = context.getResources().getColor(R.color.text_disabled);
        monthColor= context.getResources().getColor(R.color.text_month);
        monthBackgroundColor=context.getResources().getColor(R.color.transparent);
        decorator = R.drawable.selected_day_simple_decorator;
        monthTextSize = 12;
        monthAlignment=1;
        isEventColorDisable = false;

        isDividerColorChangeAt[0] = false;
        isDividerColorChangeAt[1] = true;
        isDividerColorChangeAt[0] = false;
        isDividerVisible = false;

        year_from = context.getResources().getInteger(R.integer.year_from);
        month_from = context.getResources().getInteger(R.integer.month_from);
        special_count = context.getResources().getInteger(R.integer.special_count);
        max_double_selected_count = context.getResources().getInteger(R.integer.max_double_selected_count);

        special = context.getString(R.string.special);
        today = context.getString(R.string.today);
        format_month = context.getString(R.string.format_month);
        format_date = context.getString(R.string.format_date);

    }

    //    reset calendar data
    public void resetUtil(Context context) {
        transparent = context.getResources().getColor(R.color.transparent);
        background_day = context.getResources().getColor(R.color.background_day);
        background_selected = context.getResources().getColor(R.color.background_selected);
        background_ranged = context.getResources().getColor(R.color.background_ranged);
        background_disabled = context.getResources().getColor(R.color.background_disabled);
        text_day = context.getResources().getColor(R.color.text_day);
        text_selected = context.getResources().getColor(R.color.text_selected);
        text_today = context.getResources().getColor(R.color.text_today);
        text_special = context.getResources().getColor(R.color.text_special);
        monthColor= context.getResources().getColor(R.color.text_month);
        monthBackgroundColor=context.getResources().getColor(R.color.transparent);
        monthAlignment=1;
        monthPadding[0]=0;
        monthPadding[1]=0;
        monthPadding[2]=0;
        monthPadding[3]=0;
        text_weekend = context.getResources().getColor(R.color.text_day);
        dividerColor = context.getResources().getColor(R.color.background_divider);
        text_disabled = context.getResources().getColor(R.color.text_disabled);
        isDividerVisible = false;
        decorator = R.drawable.selected_day_simple_decorator;
        isDividerColorChangeAt[0] = false;
        isDividerColorChangeAt[1] = true;
        isDividerColorChangeAt[0] = false;
        monthTextSize = 12;
        isEventColorDisable = false;


    }


    public void setDecorator(int decorator) {
        this.decorator = decorator;
    }

    public void setMonthSize(float size) {
        monthTextSize = size;
    }

    public void setDividerVisibility(boolean val) {
        isDividerVisible = val;
    }

    /*boolean size is 3 at index 0 for top divider
* index 1 for middle divider (divider between months)
* index 2 for last divider
* if val true divider  color is change*/
    public void setDividerColor(int color, boolean[] b) {
        dividerColor = color;
        isDividerColorChangeAt = b;
    }



    public void setTransparent(int transparent) {
        this.transparent = transparent;
    }

    /*  public void setBackground_day(int background_day) {
          this.background_day = background_day;
      }
  */
    public void setBackground_selected(int background_selected) {
        this.background_selected = background_selected;
    }

    public void setBackground_ranged(int background_ranged) {
        this.background_ranged = background_ranged;
    }

    public void setBackground_disabled(int background_disabled) {
        this.background_disabled = background_disabled;
    }

    public void setText_day(int text_day) {
        this.text_day = text_day;
    }

    public void setText_selected(int text_selected) {
        this.text_selected = text_selected;
    }

    public void setText_today(int text_today) {
        this.text_today = text_today;
    }

    public void setText_special(int text_special) {
        this.text_special = text_special;
    }


    public void setText_weekend(int text_weekend) {
        this.text_weekend = text_weekend;
    }

    public void setText_disabled(int text_disabled) {
        this.text_disabled = text_disabled;
    }

    public void setDisableDateEventColor(boolean isEventColorDisable) {
        this.isEventColorDisable = isEventColorDisable;
    }



}
