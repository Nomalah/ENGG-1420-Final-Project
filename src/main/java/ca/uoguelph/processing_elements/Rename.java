package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;

public class Rename implements ProcessingElement {
    public Rename(String suffix) {

    }
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("Rename");
    }
}
