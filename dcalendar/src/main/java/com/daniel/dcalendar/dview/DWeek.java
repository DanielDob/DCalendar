package com.daniel.dcalendar.dview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.daniel.dcalendar.data.Global;
import com.daniel.dcalendar.logic.view.DWeekLogic;

public class DWeek extends LinearLayout {

    private final static int NUMBER_OF_DAYS=7;
    DDay[] dDays = new DDay[7];
    int weekN;
    public DWeek(Context context, int[] daysNumber,int weekNumber) {
        super(context);
        this.weekN=weekNumber;
        setWeightSum(7);
        for(int i=0;i<NUMBER_OF_DAYS;i++){
            addView(new DDay(context,DWeekLogic.setYearToDDay(daysNumber,i, weekNumber), DWeekLogic.setMonthToDDay(daysNumber,i,weekNumber), daysNumber[i], true));
            dDays[i]=(DDay) getChildAt(i);
        }
    }

    public DWeek(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void renameDays(int[] daysNumber) {
        for(int i=0;i<NUMBER_OF_DAYS;i++){
            dDays[i].setText(String.valueOf(daysNumber[i]));
            dDays[i].setMonth(DWeekLogic.setMonthToDDay(daysNumber,i,weekN));
            dDays[i].setYear(DWeekLogic.setYearToDDay(daysNumber,i,weekN));
            dDays[i].setDay(daysNumber[i]);
            dDays[i].setSelected(false);
            dDays[i].updateShadow();
            dDays[i].setListeners();
            dDays[i].setShadow();
            dDays[i].wasSelected();
        }
    }
    public DDay getDay(int year, int month, int day){
        for (DDay d :
                dDays){
            if(d.dateCorrect(year,month,day))
                return d;
        }
        return null;

    }
}
