package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class Rename implements ProcessingElement {

    private final String target_suffix;

    public Rename(String suffix) {
        this.target_suffix = suffix;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        for (StorageElement element : input) {
            String name = element.name();
            int split_index = name.lastIndexOf("."); // Find file extension start
            String new_name
                    = name.substring(0, split_index) // Add prefix
                    + this.target_suffix // Add suffix
                    + name.substring(split_index); // Add file extension
            element.rename(new_name); // Rename the element with it's new name
        }
        return input;
    }

    @Override
    public void print() {
        System.out.println("Rename");
    }
}
