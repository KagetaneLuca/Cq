package de.nordakademie;

import de.nordakademie.experiment.ExperimentCalenderQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {

    Map<Integer, Double> timeIni = new HashMap<>();
    public static void main(String[] args) {
        int initialSize = 1000;
        int repetitions = initialSize / 10; // 10% der Basis Werte reicht als vergleich
        int n = 100;
        int m = 1000;
        exp3(n, initialSize, repetitions);

    }
    private static void exp3(int n, int initialSize, int repetitions) {
        double iniTime;
        double evaTime;
        List<Double> initialTimeList = new ArrayList<>();
        List<Double> evaluateTimeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ExperimentCalenderQueue experiment = new ExperimentCalenderQueue();
            iniTime = experiment.initialize(initialSize);
            evaTime = experiment.evaluate(repetitions);
            if (i == 0 || i == 1 || i == 99 || i == 999) {
                initialTimeList.add(iniTime);
                evaluateTimeList.add(evaTime);
                System.out.println("initial Time cal " + i + ": " + iniTime);
                System.out.println("evaluate Time cal " + i + ": " + evaTime);
            }
//            System.out.println("initial Time Bin " + i + ": " + experiment.initialize(initialSize));
//            System.out.println("evaluate Time Bin " + i + ": " + experiment.evaluate(repetitions));
        }
    }
}
