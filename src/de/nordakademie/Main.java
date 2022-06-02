package de.nordakademie;


import de.nordakademie.experiment.ExperimentCalenderQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {

    Map<Integer, Double> timeIni = new HashMap<>();
    public static void main(String[] args) {
        int initialSize = 10000;
        int repetitions = initialSize / 10; // 10% der Basis Werte reicht als vergleich
        int n = 100;
        exp3(n, initialSize, repetitions);
//        exp2(n, initialSize, repetitions);
//        exp1(n, initialSize, repetitions);
    }
    private static void exp3(int n, int initialSize, int repetitions) {
        List<Long> initialTimeList;
        List<Long> evaluateTimeList;
        for (int i = 0; i < n; i++) {
            ExperimentCalenderQueue experiment = new ExperimentCalenderQueue(initialSize);
            initialTimeList = experiment.initialize(initialSize);
            evaluateTimeList = experiment.evaluate(repetitions);
            if (i == 0 || i == 1 || i == 99 || i == 999) {
                for (int j = 0; j < initialTimeList.size(); j++) {
                    System.out.println("initial Time cal " + i + " after "+ (j+1) +  "0k repetition: " + initialTimeList.get(j));
                }
                for (int k = 0; k < evaluateTimeList.size(); k++) {
                    System.out.println("evaluate Time cal " + i + " after "+ (k+1) +  "k repetition: " + evaluateTimeList.get(k));
                }
            }
        }
    }
}
