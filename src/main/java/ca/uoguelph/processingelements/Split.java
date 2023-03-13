package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Split implements ProcessingElement {

    int target_lines;

    public Split(int target_lines) {

    }

    @Override
    // function
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
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
                String fileContents = input.get(i).read();
                String[] fileLines = fileContents.split("\n");
                String nameOfEntry = input.get(i).name();

                // count number of Lines in file
                int numOfLines = 0;
                for (int j = 0; j < fileLines.length; j++) {
                    if (fileLines[j] != null) {
                        numOfLines++;
                    }
                }
                String newName;
                int new_files;
                int num_arrays = 0;

                int rest = fileLines.length % target_lines;
                int parts = fileLines.length / target_lines + (rest > 0 ? 1 : 0);
                String[][] arrays = new String[parts][];
                for (int j = 0; j < (rest > 0 ? parts - 1 : parts); j++) {
                    arrays[i] = Arrays.copyOfRange(fileLines, j * target_lines, j * target_lines + target_lines);
                    num_arrays++;

                }
                if (rest > 0) {
                    arrays[parts - 1] = Arrays.copyOfRange(fileLines, (parts - 1) * target_lines, (parts - 1) * target_lines + rest);
                    num_arrays++;
                }

                for (int f = 0; f <= num_arrays; f++) {
                    newName = nameOfEntry + ".part" + f + 1;
                    File newFile = new File(newName);
                    try {
                        FileWriter writer = new FileWriter(newFile, true);
                    } catch (IOException ioe) {
                    }

                }

//                // if file perfectly divisible by target lines
//                if (numOfLines % target_lines == 0) {
//                    new_files = numOfLines / target_lines;
//                    // should I add this inside of rename method in storageElement?
//                    for (int f = 0; f <= new_files; f++) {
//                        newName = nameOfEntry + ".part" + f;
//                        File newFile = new File(newName);
//                        try {
//                            FileWriter writer = new FileWriter(newFile, true);
//                        } catch (IOException ioe) {
//                        }
//                        int elecount =0;
//                        String[] newPart;
//                        for (int part = 0; part < fileLines.length; target_lines++) {
//                            newPart[elecount] = part;
//                            
//                            
//                            
//                            for(int ele=0; ele<newPart.length; ele++){
//                                
//                            }
//
//                        }
//
//                    }
//
//                } else {
//                    new_files = numOfLines / target_lines + 1;
//                    for (int f = 0; f <= new_files; f++) {
//                        newName = nameOfEntry + ".part" + f;
//
//                    }
//
//                }
            }

        }

        return output;
    }

    @Override
    public void print() {
        System.out.println("Split");
    }
}
