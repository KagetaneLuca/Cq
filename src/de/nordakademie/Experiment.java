package de.nordakademie;

import de.nordakademie.dost.IEventQueueImpl;

import java.util.*;

public class Experiment {
   private IEventQueueImpl<Object> eventQueue = new IEventQueueImpl<>();
    private List<Long> times = new LinkedList<>();
   private Random rand = new Random();

    public double initialize(int initialSize){
        long startTime = System.nanoTime();
        for (int i = 0; i < initialSize; i++) {
            eventQueue.enqueue(rand.nextDouble(), new Object());

        }
        return  (float)(System.nanoTime() - startTime);
    }
    public double evaluate(int repetitions){
        long startTime;
        long endTime;

        for (int i = 0; i < repetitions; i++) {
            startTime = System.nanoTime();
            eventQueue.enqueue(rand.nextDouble(), new Object());
            eventQueue.dequeue();
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
