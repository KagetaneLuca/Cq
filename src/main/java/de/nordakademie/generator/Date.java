package de.nordakademie.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * @param incommingDate Object to compare
     * @return true if its equal
     */
    public boolean equalTo(Date incommingDate) {
        if (this.getYear() != incommingDate.getYear()) {
            return false;
        } else {
            if (this.getMonth() != incommingDate.getMonth()) {
                return false;
            } else {
                if (this.getDay() != incommingDate.getDay()) {
                    return false;
                } else {
                    if (this.getHour() != incommingDate.getHour()) {
                        return false;
                    } else {
                        return this.getMinute() == incommingDate.getMinute();
                    }
                }
            }
        }
    }
}
