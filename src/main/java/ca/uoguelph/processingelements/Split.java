package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class Split implements ProcessingElement {
    public Split(long lines) {

    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    @Override
    public void print() {
        System.out.println("Split");
    }
}
