package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.LocalStorageElement;
import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

                // check if array can be split into multiple equal arrays
                int rest = fileLines.length % target_lines;
                // check how manny arrays input array can be split into
                int parts = fileLines.length / target_lines;
                // create array of required size
                int count = 0;

                for (int j = 0; j < parts; j++) {
                    count++;

                    Path file = Path.of(nameOfEntry + ".part" + count);

                    for (int k = 0; k < target_lines; k++) {
                        try {
                            Files.writeString(file, fileLines[k + (target_lines * j)]);
                        } catch (IOException ex) {
                            System.out.println("not a file");

                        }

                    }
                    LocalStorageElement ele = new LocalStorageElement(file.toFile().getPath());
                    output.add(ele);

                }

                if (rest != 0) {
                    count++;

                    Path file = Path.of(nameOfEntry + ".part" + count);

                    for (int r = 0; r < rest; r++) {
                        try {
                            Files.writeString(file, fileLines[r + (target_lines * parts)]);
                        } catch (IOException ex) {
                            System.out.println("not a file");

                        }

                    }
                    LocalStorageElement ele = new LocalStorageElement(file.toFile().getPath());
                    output.add(ele);

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
