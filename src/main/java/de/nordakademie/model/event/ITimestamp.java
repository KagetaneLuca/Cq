package de.nordakademie.model.event;

public interface ITimestamp {
    IDate getDate();
    int getHour();
    int getMinute();
    String toString();
}
