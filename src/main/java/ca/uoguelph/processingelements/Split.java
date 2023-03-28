package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Split implements ProcessingElement {

    int target_lines;

    public Split(int target_lines) {
        this.target_lines = target_lines;

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

                
                String newName;
                int num_arrays = 0;

                // check if array can be split into multiple equal arrays
                int rest = fileLines.length % target_lines;
                // check how manny arrays input array can be split into
                int parts = fileLines.length / target_lines + (rest > 0 ? 1 : 0);
                // create array of required size
                String[][] arrays = new String[parts][];
                for (int j = 0; j < (rest > 0 ? parts - 1 : parts); j++) {
                    // copies into new array
                    arrays[i] = Arrays.copyOfRange(fileLines, j * target_lines, j * target_lines + target_lines);
                    num_arrays++;
                }
                // If rest>0, last array will contain less elements
                if (rest != 0) {
                    arrays[parts - 1] = Arrays.copyOfRange(fileLines, (parts - 1) * target_lines, (parts - 1) * target_lines + rest);
                    num_arrays++;
                }
                // create new files for corresponding num of arrays
                for (int f = 0; f <= num_arrays; f++) {
                    newName = nameOfEntry + ".part" + f + 1;
                    PrintStream ps;
                    try {
                        ps = new PrintStream(new FileOutputStream(newName));
                        // how to only add each consectutive array to a new file?
                        // I have a feeling this won't work
                        for (int row = 0; row < row + 1; row++) {
                            for (int col = 0; col < col + 1; col++) {
                                String s = arrays[row][col];
                                ps.println(s);

                            }
                        }
                        ps.close();
                    } catch (FileNotFoundException e) {

                    }

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
