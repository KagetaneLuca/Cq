package de.nordakademie.model.event.impl;

import de.nordakademie.model.event.IDate;
import de.nordakademie.model.event.ITimestamp;

public class Timestamp implements ITimestamp {
    private IDate date;
    private int hour;
    private int minute;

    /**
     * @implNote single values constructor
     */
     Timestamp(int year, int month, int day, int hour, int minute){
        setDate(year, month, day);
    }
    /**
     * @implNote hour and minute combined in time
     * @param time Format: "hh:mm"
     */
    Timestamp(int year, int month, int day, String time){
        timeStringToInt(time);
        setDate(year, month, day);
    }
    private void timeStringToInt(String time) {
        String[] temp = time.split(":");
        hour = Integer.parseInt(temp[0]);
        minute = Integer.parseInt(temp[1]);
    }

    private void setDate(int year, int month, int day) {
        date = new Date(year, month, day);
    }
    @Override
    public IDate getDate() {
        return date;
    }
    @Override
    public int getMinute() {
        return minute;
    }
    @Override
    public int getHour() {
        return hour;
    }
    @Override
    public String toString(){
        return String.valueOf(date.getYear() + "/" + date.getMonth() + "/" + date.getDay() + "|" + hour + ":" + minute);
    }
}
