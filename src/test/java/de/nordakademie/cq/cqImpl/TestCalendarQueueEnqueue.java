package de.nordakademie.cq.cqImpl;

import org.junit.jupiter.api.*;

import java.util.GregorianCalendar;
import java.util.Date;

public class TestCalendarQueueEnqueue {
private CalendarQueue calendarQueue;

private GregorianCalendar gregorianCalendar;
private final int treeSize = 10;

private Double elementNumber = 0D;

    @Test
    @DisplayName("enqueue Element in nearFurture")
    public void enqueue(){
        calendarQueue = new CalendarQueue(treeSize);
        gregorianCalendar= new GregorianCalendar();
//        calendarQueue.enqueue(1D, "Test");
        Assertions.assertEquals("Test", calendarQueue.getNearFuture().get(0).getEventDescription());
    }

    @Test
    @DisplayName("enqueue Element in tree")
    public void enqueueElementInTree(){
        calendarQueue = new CalendarQueue(treeSize);
        for (int i = 0; i < 13; i++) {
//            calendarQueue.enqueue(elementNumber, String.valueOf(elementNumber));
            elementNumber++;
        }
        Node.Splaytree tree = calendarQueue.getTree();
        Node node = tree.minimum();

        Assertions.assertEquals(10D, node.key.getTimestamp());
    }

    @Test
    @DisplayName("enqueue Element in farFuture")
    public void enqueueElementinFarFuture(){
        calendarQueue = new CalendarQueue(10);
        elementNumber = 0.;
        for (int i = 0; i < 10; i++) {
//            calendarQueue.enqueue(elementNumber, String.valueOf(elementNumber));
            Assertions.assertEquals(i+1, calendarQueue.getNearFuture().size(), "near future enqueue "+i);
            Assertions.assertEquals(0, calendarQueue.getFarFutrure().size(),"far future size unchanged");
            elementNumber++;
        }
        for (int i = 0; i < 10; i++) {
//            calendarQueue.enqueue(elementNumber, String.valueOf(elementNumber));
            Assertions.assertEquals(10, calendarQueue.getNearFuture().size(), "near future constant size");
            Assertions.assertEquals(0, calendarQueue.getFarFutrure().size(),"far future size unchanged");
            Assertions.assertEquals(1+ i, calendarQueue.getTree().size(calendarQueue.getTree().root), "mid future enqueue "+i);

            elementNumber++;
        }
        for (int i = 0; i < 10; i++) {
//            calendarQueue.enqueue(elementNumber, String.valueOf(elementNumber));
            Assertions.assertEquals(10, calendarQueue.getNearFuture().size(), "near future constant size");
            Assertions.assertEquals(10, calendarQueue.getTree().size(calendarQueue.getTree().root), "mid future constant size");
            Assertions.assertEquals(i + 1, calendarQueue.getFarFutrure().size(),"far future enqueue");
            elementNumber++;
        }
        Assertions.assertEquals("20.0", calendarQueue.getFarFutrure().get(0).getEventDescription());
    }
}
