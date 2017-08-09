# RecyclerCalendarView
RecyclerCalendarView is Calendar Library that shows Months in form of list and offer customizations.

# Example usage
Add `RecyclerCalendarView` to your view hierarchy like this:

```xml
<com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView
        android:layout_width="match_parent"
        android:layout_height="470dp"
        android:id="@+id/recyclerCalendarView"/>
```

Customization
-------------------

You can customize some attributes of the `RecyclerCalendarView` in xml.

```
        app:showFixedHeader="true"
        app:selectionBackgroundColor="@color/colorAccent"
        app:showMonthDivider="true"
```


### Dynamically customization


#### Set Min-Max Dates

`setMinDate` method use to set min date on calendar it takes 3 arguments of int type `year,month and day` or two argument `year and month`, similarly setMaxDate is use to set max date on calendar.

```
setMinDate(int year, int month, int day);
or
setMinDate(int year, int month); //day automatically assigned to 1

setMaxDate(int year, int month, int day);
or
setMaxDate(int year, int month); //day automatically assigned to 1
```
```
 mRecyclerCalendarView.setMinDate(2017,8,8); 
 // or
 mRecyclerCalendarView.setMinDate(2017,8); //day automatically assigned to 1
 
 mRecyclerCalendarView.setMaxDate(2018,5,2);
 // or
 mRecyclerCalendarView.setMaxDate(2018,5); //day automatically assigned to 1
```

#### Set Special Dates

Special events can be set programmatically by calling method `setEvent` that takes List of int array of size 3 where index 0 is year, index 1 is month and index 2 is day
```
setEvent(List int[] dates);  //dates is an integer array of size 3 where index 0 contains year index 1 contains Month index 2 contains Day
```
```
 specialEvents = new ArrayList<>();
        int[] eDates = {2017, 8, 2};
        int[] eDates1 = {2017, 8, 3};
        int[] eDates2 = {2017, 8, 12};
        int[] eDates3 = {2017, 8, 9};
        specialEvents.add(eDates);
        specialEvents.add(eDates1);
        specialEvents.add(eDates2);
        specialEvents.add(eDates3);
        
 mRecyclerCalendarView.setEvent(specialEvents);
```
#### Set Disable Dates

Disable events can be set programmatically by calling method `setDisableDates` that takes List of int array of size 3 where index 0 is year, index 1 is month and index 2 is day
```
setDisableDates(List int[] dates);  //dates is an integer array of size 3 where index 0 contains year index 1 contains Month index 2 contains Day
```
```
 disableDates = new ArrayList<>();
        int[] dDates = {2017, 8, 2};
        int[] dDates1 = {2017, 8, 4};
        int[] dDates2 = {2017, 8, 10};
        int[] dDates3 = {2017, 8, 9};
        disableDates.add(dDates);
        disableDates.add(dDates1);
        disableDates.add(dDates2);
 mRecyclerCalendarView.setDisableDates(disableDates);
```

OR use `setDisableDates(List<int[]> disableDates, boolean isEventColorDisable)`,  boolean paramenter if true than event on disable date will also be of same color as disable date else events on disable day will be highlighted
```
 mRecyclerCalendarView.setDisableDates(disableDates,true);
```
#### Date Selection Mode

User can select single date or range of dates:
`By default single selection date is enable`
* Single Selection Mode
* Double Selection Mode
```
setDoubleSelectedMode(boolean val); 
setDoubleSelectionMode(false); // pass false for single selection mode
setDoubleSelectionMode(true); // pass true for double selection mode
```
#### Customize Fixed Month Header

Fixed Month Header can be customize:
* Visibilty
* Alignment
* Change Background Color
* Get Header

##### Visibility of Fixed Month Header

You Can Hide or Show Fixed Month Header: `By default it is  hidded`
```
showMonthHeader(boolean val);
```
##### Align Month Text in Fixed Header
Month Text View postion can be change using `alignFixedHeaderTextView(int pos)`: By default it is aligned `center`
* RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_START
* RecyclerCalendarView.TEXT_ALIGNMENT_CENTER
* RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END
```
mRecyclerCalendarView.setMonthTextAlignment(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_START);

```

##### Set Background color Fixed Month Header
```
setFixedHeaderColor(int color);
```
##### Get Fixed Month Header TextView
```
getFixedHeaderTextView(); // this will return fixed header TextView
```
#### Set Calendar Background Color

To set calendar background color call `setBgColor(int color)` on RecyclerCalendarView object
```
 mRecyclerCalendarView.setBgColor(Color.BLUE);
```
#### Customize Month

Month can be customize:
* Alignment
* Set Padding
* Change Size
* Change Background Color
* Change Text Color
##### Align Months
`setMonthTextAlignment(int pos)` is use to align months : By default it is align to centre
* RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_START
* RecyclerCalendarView.TEXT_ALIGNMENT_CENTER
* RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END
```
  mRecyclerCalendarView.setMonthTextAlignment(RecyclerCalendarView.TEXT_ALIGNMENT_TEXT_END);
```
##### Set Padding to Months
setMonthPadding(int left, int top, int right, int bottom);
```
 mRecyclerCalendarView.setMonthPadding(60,0,0,0);
```
##### Set Month Size
```
mRecyclerCalendarView.setMonthTextViewSize(size);
```
##### Set Month Background Color
```
mRecyclerCalendarView.setMonthBackgroundColor(color);
```
##### Set Month Text Color
```
mRecyclerCalendarView.setMonthTextColor(color);
```
#### Decorator
You can change selection day decorator, 3 in-built Decorators are provided in the library but you can use your custom decorator also
##### Built-In Decorators
`setPresetDecoratorItem(int decorator)` is use for using Preset decorators:
* SIMPLE_DECORATOR
* SIMPLE_OUTLINE_DECORATOR
* DESIGNER_DECORATOR
```
 mRecyclerCalendarView.setPresetDecoratorItem(RecyclerCalendarView.DESIGNER_DECORATOR);
```
##### Custom Decorator
`setDecoratorItem(int res)` by using it ou can set decorator from drawable, shapes etc.
```
mRecyclerCalendarView.setDecoratorItem(R.drawable.ic_my_selector);
```
#### Set Selected Day Background Color
Set selection Background Color
```
mRecyclerCalendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.primary_darker_blue));
```
#### Day Text Colors
Following Day Text color can be customize:
* Today
* Disable Day
* Special/Event Day
* Weekends
* Normal Day
##### Today day color
Pass color resource or color to `setTodayColor(color)`
```
mRecyclerCalendarView.setTodayColor(Color.RED);
or
mRecyclerCalendarView.setTodayColor(getResources().getColor(R.color.colorAccent));
```
##### Today Disable days color
Pass color resource or color to `setDisableDayColor(color)`
```
mRecyclerCalendarView.setDisableDayColor(Color.GREY);
or
mRecyclerCalendarView.setDisableDayColor(getResources().getColor(R.color.myDisableColor));
```
##### Today Event days color
Pass color resource or color to `setEventColor(color)`
```
mRecyclerCalendarView.setEventColor(Color.BLUE);
or
mRecyclerCalendarView.setEventColor(getResources().getColor(R.color.myEventColor));
```

##### Today Weekend days color
Pass color resource or color to `setWeekendDayColor(color)`
```
mRecyclerCalendarView.setWeekendDayColor(Color.RED);
or
mRecyclerCalendarView.setWeekendDayColor(getResources().getColor(R.color.weekendColor));
```
##### Today Normal days color
Pass color resource or color to `setDayColor(color)`
```
mRecyclerCalendarView.setDayColor(Color.BLACK);
or
mRecyclerCalendarView.setDayColor(getResources().getColor(R.color.dayColor));
```
#### Month Devider
Month divider by default is hidden, but its visiblilty can be change and its color can also be modified
##### Month Divider visibility
`setMonthDividerVisible(boolean val)` pass true to make divider visible
```
mRecyclerCalendarView.setMonthDividerVisible(true);
```
##### Change Divider Color
```
mRecyclerCalendarView.setDividerColor(color); // by default only middle dividle color changes

```

You can decide which divider color should change by `mRecyclerCalendarView.setDividerColor(int color,boolean top,boolean middle,boolean bottom)` and pass boolean to change color of top, middle and bottom divider

#### Reset/Clear selection
Calendar Data can be reset by calling `resetCalendar()`
```
mRecyclerCalendarView.resetCalendar();
```
To clear selection call `resetSelected()`
```
mRecyclerCalendarView.resetSelected();
```
#### Get Date
The return type of date is an array of int of size 3 where:
* Index 0 - Year
* Index 1 - Month 
* Index 2 - Day
##### Get Selected Date

```
mRecyclerCalendarView.getSelectedDate();
```
##### Get Today Date
```
mRecyclerCalendarView.getTodayDate();
```

Notes
-------------------
Date is in int array of size 3 where:
* Index 0 - Year
* Index 1 - Month 
* Index 2 - Day
Month Values lies bwteen `1=January` to `12=December`</br>
Days before Todays date are Disabled.

### MIT License

```
    The MIT License (MIT)
    
    Copyright (c) 2014 Junguan Zhu
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
```
