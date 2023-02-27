package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class LengthFilter implements ProcessingElement {
    public LengthFilter(long length, String operator) {

    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        return input;
    }

    @Override
    public void print() {
        System.out.println("LengthFilter");
    }
}
