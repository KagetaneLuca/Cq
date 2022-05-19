package de.nordakademie;

import de.nordakademie.cq.ICalendarQueue;
import de.nordakademie.dost.IEventQueueImpl;

import java.io.FileNotFoundException;
import java.util.*;

public class Experiment {
   private IEventQueueImpl<Object> eventQueue = new IEventQueueImpl<>();
    private List<Long> times = new LinkedList<>();
   private Random rand = new Random();

   private List<Double> dataset;
   private List<Double> dataset2;

    public double initialize(int initialSize) {
        InputGen inputGen = new InputGen();
        List<Double> doubleList = inputGen.readToList();
        if (doubleList == null || doubleList.size() > initialSize) {
            return -1;
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < initialSize; i++) {
            eventQueue.enqueue(doubleList.get(0), new Object());

        }
        return  (float)(System.nanoTime() - startTime);
    }
    public double evaluate(int repetitions){
        long startTime;
        long endTime;

        for (int i = 0; i < repetitions; i++) {
            startTime = System.nanoTime();
           IEventQueue.Entry<Object> event = eventQueue.dequeue();
            eventQueue.enqueue(event.getTime() + rand.nextDouble(), new Object());
            endTime = System.nanoTime()- startTime;
            if(times.isEmpty()){
                times.add(endTime);
            }
            for (int j = 0; j < times.size(); j++) {
                if (endTime < times.get(j)){
                    times.add(j, endTime);
                    break;
                }
            }
        }
        return     times.stream().mapToDouble(a -> a).average().getAsDouble();
    }
}
