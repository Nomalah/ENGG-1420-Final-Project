package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;

public class LengthFilter implements ProcessingElement {
    public LengthFilter(long length, String operator) {

    }

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    public void print(){
        System.out.println("LengthFilter");
    }
}