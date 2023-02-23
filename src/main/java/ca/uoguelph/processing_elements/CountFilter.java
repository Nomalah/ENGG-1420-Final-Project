package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;

public class CountFilter implements ProcessingElement {
    public CountFilter(String search_key, int min_count) {

    }

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("CountFilter");
    }
}