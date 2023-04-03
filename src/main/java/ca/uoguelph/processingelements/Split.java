package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.LocalStorageElement;
import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Split implements ProcessingElement {

    // parameter of split method 
    long target_lines;

    // split method constructor
    public Split(long target_lines) {
        this.target_lines = target_lines;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        // create new object of type storage element
        ArrayList<StorageElement> output = new ArrayList<>();
        // iterate through output ArrayList
        for (StorageElement element : input) {
            // if directory, no changes made
            if (element.isDirectory() || !(element instanceof LocalStorageElement)) { // To split, must be on local storage
                output.add(element); // Pass directories through
                // if file case 
            } else {
                // read in file contents using .read(), a method of local storage element
                // return file as single string
                String fileContents = element.read();
                // split into file lines at each space 
                String[] fileLines = fileContents.split("\n");
                String nameOfPath = ((LocalStorageElement) element).getFilePath().toString();
                // split name of path before extension to later change part names
                int splitIndex = nameOfPath.lastIndexOf(".");
                // check if array can be split into multiple equal arrays
                long rest = fileLines.length % target_lines;// if rest is not 0 can not be split equal
                // check how many parts input array can be split into
                long parts = fileLines.length / target_lines;

                int count = 0;

                // iterate through number of parts for split
                for (int j = 0; j < parts; j++) {
                    count++;
                    // get path of each new part file 
                    Path file = Path.of(nameOfPath.substring(0, splitIndex) + ".part" + count + nameOfPath.substring(splitIndex));
                    // initilize content string 
                    String content = "";
                    // loop through original file to start adding lines from next part
                    for (int k = 0; k < target_lines; k++) {
                        content += (fileLines[k + ((int) target_lines * j)] + "\n");
                    }
                    // write each part to new file 
                    try {
                        Files.writeString(file, content);
                        // file exception
                    } catch (IOException ex) {
                        System.out.println("not a file");
                    }
                    // add parts to new local storage elements
                    output.add(new LocalStorageElement(file.toFile().getPath()));
                }
                // if there is a remainder for split, add rest into new part file
                if (rest != 0) {
                    count++;
                    // path of rest file 
                    Path file = Path.of(nameOfPath.substring(0, splitIndex) + ".part" + count + nameOfPath.substring(splitIndex));
                    // initilize content string 
                    String content = "";
                    // add required file lines into rest file 
                    for (int r = 0; r < rest; r++) {
                        content += (fileLines[r + ((int) target_lines * (int) parts)] + "\n");

                    }
                    // write rest to new file 
                    try {
                        Files.writeString(file, content);
                        // file exception 
                    } catch (IOException ex) {
                        System.out.println("not a file");
                    }
                    // add rest file to new local storage element 
                    output.add(new LocalStorageElement(file.toFile().getPath()));
                }
            }
        }
        // return part files 
        return output;
    }

    @Override
    public void print() {
        System.out.println("Split");
    }
}
