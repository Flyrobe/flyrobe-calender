package com.animator_abhi.recyclerviewcalendar;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 日历实体类.
 */
final public class CalendarEntity {
    /**
     * Calendar Entity.
     */

    public static int decorator;

    public static List<CalendarEntity> newCalendarData(boolean doubleSelectedMode, int[] todayDate, int[] minDate, int[] maxDate, List<int[]> eventDate, List<int[]> disableDate) {
        List<CalendarEntity> calendarData = new ArrayList<>();

        int[] specialDateBefore = Util.addDate(todayDate, doubleSelectedMode ? 0 : Util.getInstance().special_count);

        // int yearTo = specialDateBefore[0];
        //  int monthTo = specialDateBefore[1];
        int CurrentYear = todayDate[0];
        int currentMonth = todayDate[1];
        int week = Util.getWeek(new int[]{CurrentYear, currentMonth, 1});

        int minYear = CurrentYear;
        int minMonth = currentMonth;
        int minDay = 1;

        List<int[]> eventDates = eventDate;
        List<int[]> disableDates = disableDate;

        int[] defaultMinDate = {minYear, minMonth, minDay};
        if (minDate[0] > 0 && minDate[1] > 0) {
            minYear = minDate[0];
            minMonth = minDate[1];
            week = Util.getWeek(new int[]{minYear, minMonth, 1});
            defaultMinDate = minDate;
        }

        int maxMonth = 11;
        int maxYear = minYear;
        int maxDay = Util.getDaysOfMonth(maxYear, maxMonth);

        int[] defaultMaxDate = {maxYear, maxMonth, maxDay};


        if (maxDate[0] != 0 && maxDate[1] != 0) {
            maxYear = maxDate[0];
            maxMonth = maxDate[1];
            maxDay = maxDate[2];
            defaultMaxDate = maxDate;
        }


        int weekOfFirstDayOfMonth = week;


        for (int year = minYear; year <= maxYear; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == minYear && month < minMonth
                        || year == maxYear && month > maxMonth) {
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
                            specialDateBefore, week, lastSundayOfMonth, doubleSelectedMode, eventDates, disableDates, defaultMinDate, defaultMaxDate);
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


    /**
     * TYPE MONTH.
     */
    public static final int ITEM_TYPE_MONTH = 0;
    /**
     * TYPE DAY.
     */
    public static final int ITEM_TYPE_DAY = 1;
    /**
     * TYPE EMPTY DAY.
     */
    public static final int ITEM_TYPE_EMPTY_DAY = 2;
    /**
     * TYPE DIVIDER.
     */
    public static final int ITEM_TYPE_DIVIDER = 3;

    /**
     * TYPE UNSELECTED.
     */
    public static final int SELECTED_TYPE_UNSELECTED = 0;
    /**
     * TYPE SELECTED.
     */
    public static final int SELECTED_TYPE_SELECTED = 1;
    /**
     * TYPE RANGE.
     */
    public static final int SELECTED_TYPE_RANGED = 2;

    /**
     * ITEM TYPE.
     */
    public final int itemType;

    /**
     * DATE.
     */
    public final int[] date;

    /**
     * String for EVENTS.
     */
    public final String special;


    /**
     * WEEK.
     */
    public final int week;

    /**
     * boolean whether for today.
     */
    public final boolean isToday;
    /**
     * boolean for present.
     */
    public final boolean isPresent;

    /**
     * boolean for whether day is disable.
     */
    public final boolean isDisable;
    /**
     * boolean for whether day has event.
     */
    public final boolean isSpecial;
    /**
     * boolean for whether day is enable.
     */
    public final boolean isEnabled;


    /**
     * boolean for weekend.
     */
    public final boolean isWeekend;

    /**
     * boolean for isLastSundayOfMonth
     */
    public final boolean isLastSundayOfMonth;

    /**
     * month string.
     */
    public final String monthString;
    /**
     * day string.
     */
    public final String dayString;
    /**
     * event string.
     */
    public final String specialString;

    /**
     * selected type.
     */
    public int selectedType;

    /**
     * May create an object type or type of blank day.
     */
    private CalendarEntity(int year, int month, int itemType) {
        this.itemType = itemType;
        this.date = new int[]{year, month, 0};
        this.special = null;
        this.week = -1;
        this.isToday = false;
        this.isPresent = false;
        this.isSpecial = false;
        this.isDisable = false;
        this.isEnabled = false;
        this.isWeekend = false;
        this.isLastSundayOfMonth = false;
        this.monthString = String.format(Util.getInstance().format_month, year, month);
        this.dayString = null;
        this.specialString = null;
        this.selectedType = SELECTED_TYPE_UNSELECTED;
    }

    /**
     * create object.
     */
    private CalendarEntity(int[] date, int[] todayDate, int[] specialDateBefore, int week, int lastSundayOfMonth,
                           boolean doubleSelectedMode, List<int[]> dates, List<int[]> disableDates, int[] minDate, int[] maxDate) {

        this.itemType = ITEM_TYPE_DAY;
        this.date = date;
        this.special = Util.getInstance().special;
        this.week = week;
        this.isToday = Util.isDateEqual(date, todayDate);
        this.isPresent = Util.isDateAfter(date, todayDate, true);
        this.isDisable = Util.isDateEqual(date, disableDates);

        // this.isSpecial = Util.isDateBetween(date, todayDate, specialDateBefore, false, true);
        this.isSpecial = Util.isDateEqual(date, dates);
        //pass argument false if you want current day also to be consider in isDateBefore/After
        if (isDisable || Util.isDateAfter(date, maxDate, false) || Util.isDateBefore(date, minDate, false)) {
            this.isEnabled = false;
        } else this.isEnabled = isPresent;//|| isSpecial;
        this.isWeekend = week == 0 || week == 6;
        this.isLastSundayOfMonth = date[2] == lastSundayOfMonth;
        this.monthString = String.format(Util.getInstance().format_month, date[0], date[1]);
        // this.dayString = isToday ? Util.getInstance().today : String.valueOf(date[2]);
        this.dayString = String.valueOf(date[2]);
        this.specialString = isSpecial ? TextUtils.isEmpty(special) ? "" : special : null;
        this.selectedType = doubleSelectedMode || !isToday ? SELECTED_TYPE_UNSELECTED : SELECTED_TYPE_SELECTED;
    }

    /**
     * Create an object of type divider.
     */
    private CalendarEntity() {
        this.itemType = ITEM_TYPE_DIVIDER;
        this.date = null;
        this.special = null;
        this.week = -1;
        this.isToday = false;
        this.isPresent = false;
        this.isSpecial = false;
        this.isDisable = false;
        this.isEnabled = false;
        this.isWeekend = false;
        this.isLastSundayOfMonth = false;
        this.monthString = null;
        this.dayString = null;
        this.specialString = null;
        this.selectedType = SELECTED_TYPE_UNSELECTED;
    }

    /**
     * return text colors.
     */
    public int getTextColor() {
        // when its not a day.
        if (itemType != ITEM_TYPE_DAY) {
            return Util.getInstance().transparent;
        }

        // when day is disable.
        if (!isEnabled) {
            return Util.getInstance().text_disabled;
        }

        // day is selected.
        if (selectedType != SELECTED_TYPE_UNSELECTED) {
            return Util.getInstance().text_selected;
        }

        // today day color.
        if (isToday) {
            return Util.getInstance().text_today;
        }

        // events color.
        if (isSpecial) {
            return Util.getInstance().text_special;
        }


        // weekend color.
        if (isWeekend) {
            return Util.getInstance().text_weekend;
        }

        // normal day color.
        return Util.getInstance().text_day;
    }


    /**
     * Returns the background color.
     */
    public int getBackgroundColor() {
        // not a day.
        if (itemType != ITEM_TYPE_DAY) {
            return Util.getInstance().transparent;
        }

        // disable day.
        if (!isEnabled) {
            return Util.getInstance().background_disabled;
        }

        // selected type.
        switch (selectedType) {
            case SELECTED_TYPE_SELECTED: {

                decorator = Util.getInstance().decorator;

                //  return Util.getInstance().background_selected; //return color
                return decorator; // return shape
            }
            case SELECTED_TYPE_RANGED: {
                // return Util.getInstance().background_ranged;
                decorator = Util.getInstance().decorator;

                return decorator;
            }
            default: {
                return Util.getInstance().background_day;
            }
        }
    }
}
