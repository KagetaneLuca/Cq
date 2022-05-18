package de.nordakademie.dostBinary;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExperimentBin {
   private EventQueueBinaryLink<Object> eventQueueBinaryLink;
    private List<Long> times = new LinkedList<>();
   private Random rand = new Random();

    public double initialize(int initialSize){
       eventQueueBinaryLink = new EventQueueBinaryLink<>(initialSize);
        long startTime = System.nanoTime();
        for (int i = 0; i < initialSize; i++) {
            eventQueueBinaryLink.enqueue(rand.nextDouble(), new Object());

        }
        return  (float)(System.nanoTime() - startTime);
    }
    public double evaluate(int repetitions){
        long startTime;
        long endTime;

        for (int i = 0; i < repetitions; i++) {
            startTime = System.nanoTime();
            eventQueueBinaryLink.enqueue(rand.nextDouble(), new Object());
            eventQueueBinaryLink.dequeue();
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
