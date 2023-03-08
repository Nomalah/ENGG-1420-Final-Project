package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Split implements ProcessingElement {

    int lines;
    int length; // do I need variable for this or does it come from length filter?

    public Split(int lines) {

    }

    @Override
    // function
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        int count = 0;
        // iterate through output ArrayList
        for (int i = 0; i < input.size(); i++) {
            boolean bool;
            // create bool to check if element is directory
            bool = input.get(i).isDirectory();
            // if directory, no changes made
            if (bool == true) {
                return input;
                // else is file
            } else if (bool == false) {
                // create a new array list with each element as new line 
                List<String> linesList = new ArrayList<>();
                BufferedReader br = null;
                count++;

                try {
                    // do we need to keep original file names?
                    br = new BufferedReader(new FileReader("file" + count + ".txt"));
                    String line;

                    // read file line by line for input # of lines
                    while ((line = br.readLine()) != null) {
                        // add each line to array list
                        linesList.add(line);

                    }
                    int check = lines % length;
                    int numofFiles, fileLines, remLines;
                    File f;
                    if (check == 0) {
                        numofFiles = check;
                        fileLines = lines;

                        // create number of required files
                        for (int j = 0; j < numofFiles; j++) {
                            // naming convention for new files
                            f = new File("file" + i + "_copy.txt");
                            
                            // add specified lines from array list to each file here!
                        }

                    } else {
                        numofFiles = check + 1;
                        for (int j = 0; j < check + 1; j++) {
                            fileLines = lines;
                        }
                        remLines = check;
                        // create number of required files
                        for (int k = 0; k < check + 1; check++) {
                            f = new File("file" + i + "_copy.txt");
                             // add specified lines from array list to each file here!
                        }

                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
        }

        return output;
    }

    @Override
    public void print() {
        System.out.println("Split");
    }
}
