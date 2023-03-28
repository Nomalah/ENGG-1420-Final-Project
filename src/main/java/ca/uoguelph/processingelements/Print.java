package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class Print implements ProcessingElement {

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        for (StorageElement element : input) {
            element.print(); // Print every element
        }
        return input; // This processing element does not modify the input
    }

    @Override
    public void print() {
        System.out.println("Print");
    }
}
