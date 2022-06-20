package de.nordakademie.cq.cqImpl;

import de.nordakademie.cq.IQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

public class TestCalendarQueueDequeue {
    private CalendarQueue calendarQueue;

    private final int treeSize = 10;

    private Double elementNumber = 0D;

    @Test
    @DisplayName("dequeue nearFuture")
    public void dequeue(){
        calendarQueue = new CalendarQueue(10);
        elementNumber = 0.;
        for (int i = 0; i < 10; i++) {
//            calendarQueue.enqueue(elementNumber, String.valueOf(elementNumber));
            Assertions.assertEquals(i+1, calendarQueue.getNearFuture().size(), "near future enqueue "+i);
            Assertions.assertEquals(0, calendarQueue.getFarFuture().size(),"far future size unchanged");
            elementNumber++;
        }
       IQueue.Entry<GregorianCalendar, Object> dequeueElement = calendarQueue.dequeue();
        Assertions.assertEquals(0, dequeueElement.getTime());
    }
}
