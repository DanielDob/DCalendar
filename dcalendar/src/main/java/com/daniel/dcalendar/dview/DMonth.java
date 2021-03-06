package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.logic.view.DMonthLogic;

import java.util.Date;

public class DMonth extends LinearLayout {

    private final static int NUMBER_OF_WEEKS=6;
    private static DWeek[] dWeeks = new DWeek[NUMBER_OF_WEEKS];
    public DMonth(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();

    }

    public DMonth(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        init();
    }
    private void init(){
        for(int i=0;i<NUMBER_OF_WEEKS;i++){
            addView(new DWeek(this.getContext(), DMonthLogic.setWeekDays(i),i));
            dWeeks[i] =(DWeek) getChildAt(i);
        }
        setToday();
        setChosen();
    }
    public static void renameDays(int year,int whichMonth) {
        for(int i=0;i<NUMBER_OF_WEEKS;i++){
            dWeeks[i].renameDays(DMonthLogic.setWeekDays(year,whichMonth,i));
        }
    }

    public static void refreshEvents() {
        for(DWeek w :
                dWeeks){
            for (DDay d :
                    w.dDays){
                d.refreshEvent();
            }
        }
    }

    public static DDay getDay(int year, int month, int date) {
        for(DWeek w :
                dWeeks){
            if(w.getDay(year, month, date)!=null) return w.getDay(year,month,date);
        }
        return null;
    }

    public static void setToday() {
        DDay today = getDay(new Date().getYear(),new Date().getMonth(), new Date().getDate());
        if(today!=null)
            today.setToday();
    }
    public static void setChosen() {
        if(Global.selectedDay==0) return;
        DDay today = getDay(Global.selectedYear,Global.selectedMonth,Global.selectedDay);
        if(today!=null)
            today.wasSelected();
    }
}
