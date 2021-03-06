package de.nordakademie;

import de.nordakademie.cq.CalendarQueue;
import de.nordakademie.cq.ExperimentCalenderQueue;
import de.nordakademie.safe.SafeExperimentBin;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
/*
    ExperimentCalenderQueue hardcoded filenames
 */


/*      initial Time Bin0: 1120099.0
        initial Time Bin0: 2888.428
        initial Time Bin1: 355800.0
        initial Time Bin1: 428.8780487804878
        initial Time Bin99: 25601.0
        initial Time Bin99: 3.9019607843137254
        initial Time 0: 1.622937344E9
        initial Time 0: 1783444.8144796381
        initial Time 1: 1.526677248E9
        initial Time 1: 381126.92063492065
        initial Time 99: 1.622788352E9
        initial Time 99: 4294.030303030303 */
public class Main {

    Map<Integer, Double> timeIni = new HashMap<>();
    public static void main(String[] args) {
        int initialSize = 100000;
        int repetitions = initialSize / 10; // 10% der Basis Werte reicht als vergleich
        int n = 100;
        exp3(n, initialSize, repetitions);
//        exp2(n, initialSize, repetitions);
//        exp1(n, initialSize, repetitions);
    }

    private static void exp1(int n, int initialSize, int repetitions) {
        double iniTime;
        double evaTime;
        for (int i = 0; i < n; i++) {
            Experiment experiment = new Experiment();
             iniTime = experiment.initialize(initialSize);
             evaTime = experiment.evaluate(repetitions);
            if (i == 0 || i == 1 || i == 99) {
                System.out.println("initial Time " + i + ": " + iniTime);
                System.out.println("evaluate Time " + i + ": " + evaTime);
            }
//            System.out.println("initial Time " + i + ": " + experiment.initialize(initialSize));
//            System.out.println("evaluate Time " + i + ": " + experiment.evaluate(repetitions));
        }
    }
    private static void exp2(int n, int initialSize, int repetitions) {
        double iniTime;
        double evaTime;
        for (int i = 0; i < n; i++) {
            SafeExperimentBin experiment = new SafeExperimentBin();
            iniTime = experiment.initialize(initialSize);
            evaTime = experiment.evaluate(repetitions);
            if (i == 0 || i == 1 || i == 99 || i == 999) {
                System.out.println("initial Time Bin " + i + ": " + iniTime);
                System.out.println("evaluate Time Bin " + i + ": " + evaTime);
            }
//            System.out.println("initial Time Bin " + i + ": " + experiment.initialize(initialSize));
//            System.out.println("evaluate Time Bin " + i + ": " + experiment.evaluate(repetitions));
        }
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
