package de.nordakademie.cq;

import de.nordakademie.IEventQueue;
import de.nordakademie.generator.InputGen;
import de.nordakademie.safe.SafeEventQueueBinaryLink;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ExperimentCalenderQueue {

        private CalendarQueue calendarQueue;

        private List<Double> dataset = new LinkedList<>();
        private List<Long> times = new LinkedList<>();

        private Random rand = ThreadLocalRandom.current();

        public double initialize(int initialSize){
            calendarQueue = new CalendarQueue(initialSize);
            InputGen inputGen = new InputGen();
            dataset = inputGen.readToList();
            long startTime = System.nanoTime();
            for (int i = 0; i < initialSize; i++) {
                calendarQueue.enqueue(dataset.get(i), new Object());

            }
            return  (float)(System.nanoTime() - startTime);
        }
        public double evaluate(int repetitions){
            long startTime;
            long endTime;

            for (int i = 0; i < repetitions; i++) {
                startTime = System.nanoTime();
                IEventQueue.Entry<Object> event = calendarQueue.dequeue();
                calendarQueue.enqueue(event.getTime() + rand.nextGaussian(), new Object());
                endTime = System.nanoTime()- startTime;
                if(times.isEmpty()){
                    times.add(endTime);
                } else {
                  endTime += times.get(times.size() - 1);
                }
            }
            return     times.stream().mapToDouble(a -> a).average().getAsDouble();
        }
}
