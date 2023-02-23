package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;

public class NameFilter implements ProcessingElement {
    public NameFilter(String search_key) {

    }

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("NameFilter");
    }
}