package de.nordakademie;


import de.nordakademie.experiment.ExperimentCalenderQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    Map<Integer, Double> timeIni = new HashMap<>();

    public static void main(String[] args) {
        int initialSize = 10000;
        int repetitions = initialSize / 10; // 10% der Basis Werte reicht als vergleich
        int n = 1000;
//        holdTest(n, initialSize, repetitions);
        udDownTest(n, initialSize, repetitions);
        // up down Enqueue ?
        // up down dequeue O(log n)
    }

    private static void holdTest(int n, int initialSize, int repetitions) {
        List<Long> initialTimeList;
        List<Long> evaluateTimeList;
        for (int i = 0; i < n; i++) {
            ExperimentCalenderQueue experiment = new ExperimentCalenderQueue(initialSize);
            initialTimeList = experiment.initialize(initialSize);
            evaluateTimeList = experiment.evaluate(repetitions);
            if (i == 0 || i == 1 || i == 10 || (i % 100) == 0) {
                for (int j = 0; j < initialTimeList.size(); j++) {
                    System.out.println("initial Time cal " + i + " after " + (j + 1) + "0k repetition: " + initialTimeList.get(j));
                }
                for (int k = 0; k < evaluateTimeList.size(); k++) {
                    System.out.println("evaluate Time cal " + i + " after " + (k + 1) + "k repetition: " + evaluateTimeList.get(k));
                }
            }
        }
    }

    private static void udDownTest(int n, int initialSize, int repetitions) {
        List<Long> upDownTimes;
        for (int i = 0; i < n; i++) {
            ExperimentCalenderQueue experiment = new ExperimentCalenderQueue(initialSize);
            upDownTimes = experiment.upDown(initialSize);
            if (i == 0 || i == 1 || i == 10 || (i % 100) == 0) {
                System.out.println("initial Time que " + i + " with size: " + initialSize + " : " + upDownTimes.get(0));
                System.out.println("dequeue Time que " + i + " with size: " + initialSize + " : " + upDownTimes.get(1));
            }
        }
    }
}
