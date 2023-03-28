package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class NameFilter implements ProcessingElement {
    final private String searchKey;
    public NameFilter(String searchKey) {
        this.searchKey = searchKey.toUpperCase(); // Force uppercase for searching
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        
        for (StorageElement element : input){
            // Check it as uppercase so that we are case insensative
            if (element.name().toUpperCase().contains(this.searchKey)) {
                output.add(element);
            }
        }

        return output;
    }

    @Override
    public void print() {
        System.out.println("NameFilter");
    }
}
