package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class List implements ProcessingElement {
    public List(long max) {

    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    @Override
    public void print() {
        System.out.println("List");
    }
}
