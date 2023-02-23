package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
public class List implements ProcessingElement {
    public List(long max) {

    }

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("List");
    }
}
