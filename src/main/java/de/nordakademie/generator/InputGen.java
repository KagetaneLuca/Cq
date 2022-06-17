package de.nordakademie.generator;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InputGen {
    // expo verteilung
    // camel vert
    // BiModale?
    // springframwaork , autowired von zufallszahlenverteilung
    private List<Double> randomDouble = new ArrayList<>();
    private List<Date> listDates = new ArrayList<>();

    public static void main(String[] args) {
        int datasetCount = 100000;
        InputGen inputGen = new InputGen();
        String nameDatasetRdm = "datasetRdm";
        String nameDatasetEvaRdm = "datasetEvaRdm";
        String nameDatasetDateRdm = "datasetDateRdm";
        String nameDatasetDateEvaRdm = "datasetDateEvaRdm";
        /*
            0 = double values
            1 = date values
         */
        int typeChoice = 1;
        inputGen.generateDate(datasetCount);
        inputGen.writeToFile(typeChoice, nameDatasetDateRdm);
    }

    public void generateDouble(int n) {
        randomDouble.add(ThreadLocalRandom.current().nextGaussian());
        double rdmValue;
        for (int i = 0; i < n; ) {
            rdmValue = randomDouble.get(i) + ThreadLocalRandom.current().nextGaussian();
            if (rdmValue >= 0) {
                randomDouble.add(rdmValue);
                i++;
            }
        }
    }

    public void generateDate(int n) { // zweites list element wird immer überschrieben endlos-schleife
        genADate();
        while(listDates.size() < n) {
            genADate();
            for (int i = 0; i < listDates.size(); i++) {
                if (listDates.get(i).equalTo(listDates.get(listDates.size()-1))) {
                    listDates.remove(listDates.size() - 1);
                    break;
                }
            }
        }
    }

    private void genADate() {
        Date date = new Date();
        int year = ThreadLocalRandom.current().nextInt(2000, 2101);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day;
        if (month % 2 == 0) {
            if (month != 2) {
                day = ThreadLocalRandom.current().nextInt(1, 31);
            } else {
                day = ThreadLocalRandom.current().nextInt(1, 29);
            }
        } else {
            day = ThreadLocalRandom.current().nextInt(1, 32);
        }
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);
        listDates.add(new Date(year, month, day, hour, minute));
    }

    public void writeToFile(int typechoise, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            if (typechoise == 0) {
                for (int i = 0; i < randomDouble.size(); i++) {
                    writer.write(randomDouble.get(i).toString() + "\n");
                }
            } else if (typechoise == 1) {
                for (int i = 0; i < listDates.size(); i++) {
                    writer.write(listDates.get(i).getYear() + ":" + listDates.get(i).getMonth()
                            + ":" + listDates.get(i).getDay() + ":" + listDates.get(i).getHour()
                            + ":" + listDates.get(i).getMinute() + "\n");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("cannot write to File");
        }
    }

    public List<GregorianCalendar> readToList(String filename, long size) {
        try {
            List<GregorianCalendar> fileInput = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String input;
            for (int i = 0; i <= size && bufferedReader.ready(); i++) {
                input = bufferedReader.readLine();
                String[] inputArray =  input.split(":");
                fileInput.add(new GregorianCalendar(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]),
                        Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]),Integer.parseInt(inputArray[4])));
            }
            return fileInput;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
