# RecyclerCalendarView
RecyclerCalendarView is Calendar Library that shows Months in form of list and offer customizations.

# Example usage
Add `RecyclerCalendarView` to your view hierarchy like this:

```xml
<com.animator_abhi.recyclerviewcalendar.RecyclerCalendarView
        android:layout_width="match_parent"
        android:layout_height="470dp"
        android:id="@+id/recyclerCalendarView"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"/>
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
