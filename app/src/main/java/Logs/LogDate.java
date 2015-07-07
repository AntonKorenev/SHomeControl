package Logs;

import java.util.Calendar;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class LogDate {
    private int mYear;
    private int mMonth;
    private int mDay;
    private float mTime;

    public LogDate() {
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        mMonth = Calendar.getInstance().get(Calendar.MONTH);
        mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                + (Calendar.getInstance().get(Calendar.MINUTE)/100f);
    }

    public LogDate(int year, int month, int day, float time) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mTime = time;
    }

    public float hash(){
        return (mYear*1000000) + (mMonth*10000) + (mDay*100) + mTime;
    }

    ///////////Getters
    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }

    public float getTime() {
        return mTime;
    }

    //////////Setters
    public void setYear(int year) {
        mYear = year;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public void setTime(float time) {
        mTime = time;
    }

    /////////Override
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ");
        sb.append(mYear).append('.');
        if(mMonth < 10){
            sb.append("0").append(mMonth).append('.');
        } else {
            sb.append(mMonth).append('.');
        }
        if(mDay < 10){
            sb.append("0").append(mDay).append(": ");
        } else {
            sb.append(mDay).append(": ");
        }
        sb.append(mTime);
        return sb.toString();
    }
}
