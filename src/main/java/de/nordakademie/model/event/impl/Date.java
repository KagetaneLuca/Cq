package de.nordakademie.model.event.impl;

import de.nordakademie.model.event.IDate;

public class Date implements IDate {
    private int day;
    private int year;
    private int month;

    Date(int day, int year, int month){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    @Override
    public int getMonth() {
        return month;
    }
    @Override
    public int getDay() {
        return day;
    }
    @Override
    public int getYear() {
        return year;
    }
}
