package de.nordakademie.generator;

import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InputGen {



    public static void main(String[] args) {
        int datasetCount = 1000000;
        int datasetEvaCount = datasetCount/10;
        InputGen inputGen = new InputGen();
        List<Double> randomDouble = inputGen.generateDouble(datasetCount);
        inputGen.writeToFile("datasetRdm.txt", randomDouble);
        randomDouble = inputGen.generateDouble(datasetEvaCount);
        inputGen.writeToFile("datasetEvaRdm.txt", randomDouble);
    }

    public List<Double> generateDouble(int n){
        List<Double> randomDouble = new ArrayList<>();
        randomDouble.add(ThreadLocalRandom.current().nextGaussian());
        double rdmValue;
        for (int i = 0; i < n;) {
            rdmValue = randomDouble.get(i) + ThreadLocalRandom.current().nextGaussian();
            if (rdmValue >= 0) {
                randomDouble.add(rdmValue);
                i++;
            }
        }
        randomDouble.remove(0);
        return randomDouble;
    }
    public void writeToFile(String filename, List<Double> randomDoubleList){
        try {
            FileWriter file = new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(file);
            for(int i = 0; i< randomDoubleList.size(); i++) {
                writer.write(randomDoubleList.get(i).toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public List<Double> readToList(String filename, int size) {
        try {
            List<Double> fileInput = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            while (bufferedReader.ready() && fileInput.size() <= size) {
                fileInput.add(Double.valueOf(bufferedReader.readLine()));
            }
            return fileInput;
        } catch (FileNotFoundException e){
            System.err.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
