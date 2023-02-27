package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class Rename implements ProcessingElement {
    public Rename(String suffix) {

    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    @Override
    public void print() {
        System.out.println("Rename");
    }
}
