package de.nordakademie.generator;

import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InputGen {
    private List<Double> randomDouble = new ArrayList<>();

    private Random rand = ThreadLocalRandom.current();
    public static void main(String[] args) {
        int datasetCount = 100000;
        InputGen inputGen = new InputGen();
        inputGen.generateDouble(datasetCount);
        inputGen.writeToFile();
    }

    public void generateDouble(int n){
        randomDouble.add(ThreadLocalRandom.current().nextGaussian());
        double rdmValue;
        for (int i = 0; i < n;) {
            rdmValue = randomDouble.get(i) + ThreadLocalRandom.current().nextGaussian();
            if (rdmValue >= 0) {
                randomDouble.add(rdmValue);
                i++;
            }
        }
    }
    public void writeToFile(){
        try {
            FileWriter file = new FileWriter("datasetRdm.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i< randomDouble.size(); i++) {
                writer.write(randomDouble.get(i).toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public List<Double> readToList(String filename, long size) {
        try {
            List<Double> fileInput = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            for (int i = 0; i <= size && bufferedReader.ready(); i++) {
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
