package com.animator_abhi.recyclerviewcalendar;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 日历实体类.
 */
final class CalendarEntity {
    /**
     * 返回一个日历数据.
     */
    public static List<CalendarEntity> newCalendarData(boolean doubleSelectedMode, int[] todayDate, int[] minDate, int[] maxDate,List<int[]> eventDate,List<int[]> disableDate) {
        List<CalendarEntity> calendarData = new ArrayList<>();

        int[] specialDateBefore = Util.addDate(todayDate, doubleSelectedMode ? 0 : Util.getInstance().special_count);

      // int yearTo = specialDateBefore[0];
      //  int monthTo = specialDateBefore[1];
        int yearTo=todayDate[0];
        int monthTo=todayDate[1];
        int week = Util.getWeek(new int[]{yearTo, monthTo, 1});

        int minYear=yearTo;
        int minMonth=monthTo;
        int minDay=1;

        List<int[]> eventDates=eventDate;
        List<int[]> disableDates=disableDate;

        int[] defaultMinDate={minYear,minMonth,minDay};
        if (minDate[0] > 0 && minDate[1] > 0) {
             minYear = minDate[0];
             minMonth = minDate[1];
             week = Util.getWeek(new int[]{minYear, minMonth, 1});
            defaultMinDate=minDate;
        }

        int maxMonth=11;
        int maxYear=minYear;
        int maxDay=Util.getDaysOfMonth(maxYear, maxMonth);

        int[] defaultMaxDate={maxYear,maxMonth,maxDay};


        if (maxDate[0] != 0 && maxDate[1] != 0) {
            maxYear = maxDate[0];
            maxMonth = maxDate[1];
            maxDay=maxDate[2];
            defaultMaxDate=maxDate;
        }



        Map<Integer, Map<Integer, Map<Integer, String>>> festivals = Util.getInstance().festivals;


       // int week = Util.getWeek(new int[]{yearTo, monthTo, 1});
        int weekOfFirstDayOfMonth = week;

      /*  for (int year = Util.getInstance().year_from; year <= yearTo; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == Util.getInstance().year_from && month < Util.getInstance().month_from
                        || year == yearTo && month > monthTo) {
                    continue;
                }*/

     /*   for (int year =yearTo; year <= Util.getInstance().year_from; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year ==yearTo && month < monthTo
                        || year == Util.getInstance().year_from && month > Util.getInstance().month_from) {
                    continue;
                }*/

        //new min max
        for (int year = minYear; year <= maxYear; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == minYear && month < minMonth
                        || year == maxYear && month >maxMonth) {
                    continue;
                }

                CalendarEntity monthCalendarEntity = new CalendarEntity(year, month, ITEM_TYPE_MONTH);
                calendarData.add(monthCalendarEntity);

                for (int emptyDay = 0; emptyDay < weekOfFirstDayOfMonth; emptyDay++) {
                    CalendarEntity emptyDayCalendarEntity = new CalendarEntity(year, month, ITEM_TYPE_EMPTY_DAY);
                    calendarData.add(emptyDayCalendarEntity);
                }

                int daysOfMonth = Util.getDaysOfMonth(year, month);
                int lastSundayOfMonth = Util.getLastSundayOfMonth(daysOfMonth, weekOfFirstDayOfMonth);

                for (int day = 1; day <= daysOfMonth; day++) {
                    CalendarEntity dayCalendarEntity = new CalendarEntity(new int[]{year, month, day}, todayDate,
                            specialDateBefore, festivals, week, lastSundayOfMonth, doubleSelectedMode,eventDates,disableDates,defaultMinDate,defaultMaxDate);
                    calendarData.add(dayCalendarEntity);

                    week = Util.addWeek(week, 1);
                }

                weekOfFirstDayOfMonth = Util.addWeek(weekOfFirstDayOfMonth, daysOfMonth);
            }
        }

        CalendarEntity dividerCalendarEntity = new CalendarEntity();
        calendarData.add(dividerCalendarEntity);

        return calendarData;
    }

    //*****************************************************************************************************************
    // 实体.

    /**
     * 月类型.
     */
    public static final int ITEM_TYPE_MONTH = 0;
    /**
     * 日类型.
     */
    public static final int ITEM_TYPE_DAY = 1;
    /**
     * 空白日类型.
     */
    public static final int ITEM_TYPE_EMPTY_DAY = 2;
    /**
     * 分隔线类型.
     */
    public static final int ITEM_TYPE_DIVIDER = 3;

    /**
     * 未选中的.
     */
    public static final int SELECTED_TYPE_UNSELECTED = 0;
    /**
     * 已选中的.
     */
    public static final int SELECTED_TYPE_SELECTED = 1;
    /**
     * 选中范围的.
     */
    public static final int SELECTED_TYPE_RANGED = 2;

    /**
     * 类型.
     */
    public final int itemType;

    /**
     * 日期.
     */
    public final int[] date;

    /**
     * 特殊.
     */


    public final String special;
    /**
     * 节日.
     */
    public final String festival;

    /**
     * 星期.
     */
    public final int week;

    /**
     * 是否为今天.
     */
    public final boolean isToday;
    /**
     * 是否为现在.
     */
    public final boolean isPresent;
    /**
     * 是否为特殊.
     */

    public final boolean isDisable;

    public final boolean isSpecial;
    /**
     * 是否为可用.
     */
    public final boolean isEnabled;
    /**
     * 是否为节日.
     */
    public final boolean isFestival;
    /**
     * 是否为周末.
     */
    public final boolean isWeekend;

    /**
     * 是否为当前月的最后一个星期日.
     */
    public final boolean isLastSundayOfMonth;

    /**
     * 月字符串.
     */
    public final String monthString;
    /**
     * 日字符串.
     */
    public final String dayString;
    /**
     * 特殊字符串.
     */
    public final String specialString;

    /**
     * 选中类型.
     */
    public int selectedType;

    /**
     * 创建月类型或空白日类型的对象.
     */
    private CalendarEntity(int year, int month, int itemType) {
        this.itemType = itemType;
        this.date = new int[]{year, month, 0};
        this.special = null;
        this.festival = null;
        this.week = -1;
        this.isToday = false;
        this.isPresent = false;
        this.isSpecial = false;
        this.isDisable=false;
        this.isEnabled = false;
        this.isFestival = false;
        this.isWeekend = false;
        this.isLastSundayOfMonth = false;
        this.monthString = String.format(Util.getInstance().format_month, year, month);
        this.dayString = null;
        this.specialString = null;
        this.selectedType = SELECTED_TYPE_UNSELECTED;
    }

    /**
     * 创建日类型的对象.
     */
    private CalendarEntity(int[] date, int[] todayDate, int[] specialDateBefore,
                           Map<Integer, Map<Integer, Map<Integer, String>>> festivals, int week, int lastSundayOfMonth,
                           boolean doubleSelectedMode,List<int[]> dates,List<int[]> disableDates,int[] minDate,int[] maxDate) {
        String festival = null;
        if (festivals.get(date[0]) != null && festivals.get(date[0]).get(date[1]) != null) {
            festival = festivals.get(date[0]).get(date[1]).get(date[2]);
        }

        this.itemType = ITEM_TYPE_DAY;
        this.date = date;
        this.special = Util.getInstance().special;
        this.festival = festival;
        this.week = week;
        this.isToday = Util.isDateEqual(date, todayDate);
        this.isPresent = Util.isDateAfter(date, todayDate, true);
        this.isDisable=Util.isDateEqual(date,disableDates);

       // this.isSpecial = Util.isDateBetween(date, todayDate, specialDateBefore, false, true);
        this.isSpecial=Util.isDateEqual(date,dates);
        //pass argument false if you want current day also to be consider in isDateBefore/After
        if (!TextUtils.isEmpty(festival)||isDisable||Util.isDateAfter(date,maxDate,false)||Util.isDateBefore(date,minDate,false))
        {
            this.isEnabled = false;
        } else this.isEnabled = isPresent ;//|| isSpecial;
        this.isFestival = !TextUtils.isEmpty(festival);
        this.isWeekend = week == 0 || week == 6;
        this.isLastSundayOfMonth = date[2] == lastSundayOfMonth;
        this.monthString = String.format(Util.getInstance().format_month, date[0], date[1]);
        this.dayString = isToday ? Util.getInstance().today : String.valueOf(date[2]);
        this.specialString = isSpecial ? TextUtils.isEmpty(special) ? "" : special : null;
        this.selectedType = doubleSelectedMode || !isToday ? SELECTED_TYPE_UNSELECTED : SELECTED_TYPE_SELECTED;
    }

    /**
     * 创建分隔线类型的对象.
     */
    private CalendarEntity() {
        this.itemType = ITEM_TYPE_DIVIDER;
        this.date = null;
        this.special = null;
        this.festival = null;
        this.week = -1;
        this.isToday = false;
        this.isPresent = false;
        this.isSpecial = false;
        this.isDisable=false;
        this.isEnabled = false;
        this.isFestival = false;
        this.isWeekend = false;
        this.isLastSundayOfMonth = false;
        this.monthString = null;
        this.dayString = null;
        this.specialString = null;
        this.selectedType = SELECTED_TYPE_UNSELECTED;
    }

    /**
     * 返回文字颜色.
     */
    public int getTextColor() {
        // 非日类型.
        if (itemType != ITEM_TYPE_DAY) {
            return Util.getInstance().transparent;
        }

        // 不可用.
        if (!isEnabled) {
            return Util.getInstance().text_disabled;
        }

        // 选中的.
        if (selectedType != SELECTED_TYPE_UNSELECTED) {
            return Util.getInstance().text_selected;
        }

        // 今天.
        if (isToday) {
            return Util.getInstance().text_today;
        }

        // 特殊.
        if (isSpecial) {
            return Util.getInstance().text_special;
        }

        // 节日.
        if (isFestival) {
            return Util.getInstance().text_festival;
        }

        // 周末.
        if (isWeekend) {
            return Util.getInstance().text_weekend;
        }

        // 默认.
        return Util.getInstance().text_day;
    }

    /**
     * 返回背景颜色.
     */
    public int getBackgroundColor() {
        // 非日类型.
        if (itemType != ITEM_TYPE_DAY) {
            return Util.getInstance().transparent;
        }

        // 不可用.
        if (!isEnabled) {
            return Util.getInstance().background_disabled;
        }

        // 选中类型.
        switch (selectedType) {
            case SELECTED_TYPE_SELECTED: {
              //  return Util.getInstance().background_selected; //return color
                return R.drawable.today_circle_background; // return shape
            }
            case SELECTED_TYPE_RANGED: {
              // return Util.getInstance().background_ranged;
                return R.drawable.today_circle_background;
            }
            default: {
                return Util.getInstance().background_day;
            }
        }
    }
}
