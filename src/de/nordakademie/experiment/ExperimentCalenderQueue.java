package de.nordakademie.experiment;

import de.nordakademie.model.eventqueue.IEventQueue;
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

    private ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    public ExperimentCalenderQueue(int initialSize){
        dataReadIn(initialSize);
    }

    private void dataReadIn(int initialSize) {
        InputGen inputGen = new InputGen();
        datasetString = new LinkedList<>();
        datasetEvaString = new LinkedList<>();
        dataset = inputGen.readToList("datasetRdm.txt", initialSize);
        datasetEva = inputGen.readToList("datasetEvaRdm.txt", initialSize);
        for (Double dataElement: dataset) {
            datasetString.add(String.valueOf(dataElement));
        }
        for (Double dataElement: datasetEva) {
            datasetEvaString.add(String.valueOf(dataElement));
        }

    }

    public List<Long> initialize(int initialSize){
        times = new LinkedList<>();
        calendarQueue = new CalendarQueue(initialSize);
        startTime = threadMXBean.getCurrentThreadCpuTime();
        for (int i = 1; i <= initialSize; i++) {
            calendarQueue.enqueue(dataset.get(i), datasetString.get(i));
            if((i % 10000) == 0){
                endTime = threadMXBean.getCurrentThreadCpuTime();
                times.add(endTime - startTime);
                startTime = threadMXBean.getCurrentThreadCpuTime();
            }

        }
        return times;
    }
    public List<Long> evaluate(int repetitions){
        times = new LinkedList<>();
        startTime = threadMXBean.getCurrentThreadCpuTime();
        for (int i = 1; i <= repetitions; i++) {
            IEventQueue.Entry<Object> event = calendarQueue.dequeue();
            calendarQueue.enqueue(datasetEva.get(i), datasetEvaString.get(i));

            if((i % 1000) == 0){
                endTime = threadMXBean.getCurrentThreadCpuTime();
                times.add(endTime  - startTime);
                startTime = threadMXBean.getCurrentThreadCpuTime();
            }
        }
        return times;
    }
}
