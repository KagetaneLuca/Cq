package de.nordakademie.experiment;

import de.nordakademie.cq.IQueue;
import de.nordakademie.generator.InputGen;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.LinkedList;
import java.util.List;

import de.nordakademie.cq.cqImpl.CalendarQueue;

public class ExperimentCalenderQueue {
    private List<Double> dataset;
    private List<Double> datasetEva;
    private List<String> datasetString;
    private List<String> datasetEvaString;
    private List<Long> times;
    private Long startTime;
    private Long endTime;
    private CalendarQueue calendarQueue;

    public ExperimentCalenderQueue(int initialSize) {
        dataReadIn(initialSize);
    }

    private void dataReadIn(int initialSize) {
        InputGen inputGen = new InputGen();
        datasetString = new LinkedList<>();
        datasetEvaString = new LinkedList<>();
        dataset = inputGen.readToList("datasetRdm.txt", initialSize);
        datasetEva = inputGen.readToList("datasetEvaRdm.txt", initialSize);
        for (Double dataElement : dataset) {
            datasetString.add(String.valueOf(dataElement));
        }
        for (Double dataElement : datasetEva) {
            datasetEvaString.add(String.valueOf(dataElement));
        }

    }

    public List<Long> initialize(int initialSize) {
        times = new LinkedList<>();
        calendarQueue = new CalendarQueue(initialSize);
        startTime = System.nanoTime();
        for (int i = 1; i <= initialSize; i++) {
            calendarQueue.enqueue(dataset.get(i), datasetString.get(i));
            if ((i % 10000) == 0) {
                endTime = System.nanoTime();
                times.add(endTime - startTime);
                startTime = System.nanoTime();
            }

        }
        return times;
    }

    public List<Long> evaluate(int repetitions) {
        times = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 1; i <= repetitions; i++) {
            IQueue.Entry<Double, Object> event = calendarQueue.dequeue();
            calendarQueue.enqueue(datasetEva.get(i), datasetEvaString.get(i));

            if ((i % 1000) == 0) {
                endTime = System.nanoTime();
                times.add(endTime - startTime);
                startTime = System.nanoTime();
            }
        }
        return times;
    }
    public List<Long> upDown(int initialSize) {
        calendarQueue = new CalendarQueue(initialSize);
        times = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 1; i < initialSize; i++) {
            calendarQueue.enqueue(datasetEva.get(i), datasetEvaString.get(i));
        }
        endTime = System.nanoTime();
        times.add(endTime - startTime);
        startTime = System.nanoTime();
        for (int i = 0; i < calendarQueue.treeSize(); i++) {
            calendarQueue.dequeue();
        }
        endTime = System.nanoTime();
        times.add(endTime - startTime);
        return times;
    }
}