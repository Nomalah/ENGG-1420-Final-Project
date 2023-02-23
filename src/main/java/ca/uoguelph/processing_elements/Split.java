package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;

public class Split implements ProcessingElement {
    public Split(long lines) {

    }

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("Split");
    }
}
