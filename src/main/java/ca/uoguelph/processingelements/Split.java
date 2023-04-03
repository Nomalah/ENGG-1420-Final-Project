package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.LocalStorageElement;
import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Split implements ProcessingElement {

    long target_lines;

    public Split(long target_lines) {
        this.target_lines = target_lines;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        // iterate through output ArrayList
        for (StorageElement element : input) {
            // if directory, no changes made
            if (element.isDirectory() || !(element instanceof LocalStorageElement)) { // To split, must be on local storage
                output.add(element); // Pass directories through
            } else {
                String fileContents = element.read();
                String[] fileLines = fileContents.split("\n");
                String nameOfPath = ((LocalStorageElement)element).getFilePath().toString();
                int splitIndex = nameOfPath.lastIndexOf(".");
                // check if array can be split into multiple equal arrays
                long rest = fileLines.length % target_lines;
                // check how manny arrays input array can be split into
                long parts = fileLines.length / target_lines;
                // create array of required size
                int count = 0;

                for (int j = 0; j < parts; j++) {
                    count++;

                    Path file = Path.of(nameOfPath.substring(0,splitIndex) + ".part" + count +nameOfPath.substring(splitIndex));
                    String content = "";
                    for (int k = 0; k < target_lines; k++) {
                      content += (fileLines[k + ((int)target_lines * j)] + "\n");
                    }
                    try {
                        Files.writeString(file, content);
                    } catch (IOException ex) {
                        System.out.println("not a file");
                    }
                    output.add(new LocalStorageElement(file.toFile().getPath()));
                }

                if (rest != 0) {
                    count++;

                    Path file = Path.of(nameOfPath.substring(0,splitIndex) + ".part" + count +nameOfPath.substring(splitIndex));
                    String content = "";
                    for (int r = 0; r < rest; r++) {
                        content += (fileLines[r + ((int)target_lines * (int)parts)] + "\n");
                        
                    }
                    try {
                        Files.writeString(file, content);
                    } catch (IOException ex) {
                        System.out.println("not a file");
                    }
                    output.add(new LocalStorageElement(file.toFile().getPath()));
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
