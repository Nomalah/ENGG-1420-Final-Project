package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class List implements ProcessingElement {

    private final long maxFolderEntries;

    public List(long max) {
        this.maxFolderEntries = max;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();

        for (StorageElement element : input) {
            if (element.isDirectory()) {
                ArrayList<StorageElement> childElements = element.getChildStorageElements();
                // If the number of max entries is less then the current number of entries, we're limited by the "max entries"
                if (maxFolderEntries < childElements.size()) { 
                    for (int j = 0; j < maxFolderEntries; j++) {
                        output.add(childElements.get(j));
                    }
                } else {
                    // If the number of max entries is greater then the current number of entries, we're limited by the number of child elements
                    for (int j = 0; j < childElements.size(); j++) {
                        output.add(childElements.get(j));
                    }
                }

            } else {
                output.add(element); //if its a file pass it through element
            }
        }
        return output;
    }

    @Override
    public void print() {
        System.out.println("List");
    }
}
