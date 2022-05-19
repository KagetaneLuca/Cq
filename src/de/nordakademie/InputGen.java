package de.nordakademie;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InputGen {
    /*
        Create an File with Events
     */
    private List<Double> randomDouble = new ArrayList<>();
//    private Random rand = new Random();


    public static void main(String[] args) {
        int datasetCount = 100000;
        InputGen inputGen = new InputGen();
        inputGen.generateDouble(datasetCount);
        inputGen.writeToFile();
    }

    public void generateDouble(int n){
        for (int i = 0; i < n; i++) {
            randomDouble.add(ThreadLocalRandom.current().nextDouble(0, Double.MAX_VALUE));

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

    public List<Double> readToList() {
        try {
            List<Double> fileInput = new ArrayList<>();
            Scanner scanner = new Scanner(new FileReader("datasetRdm.txt"));
            while (scanner.hasNextLine()) {
                fileInput.add(scanner.nextDouble());
            }
            return fileInput;
        } catch (FileNotFoundException e){
            System.err.println("File not found");
        }
        return null;
    }
}
