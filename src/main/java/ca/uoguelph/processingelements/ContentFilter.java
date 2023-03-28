package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class ContentFilter implements ProcessingElement {

    private final String searchKey;

    public ContentFilter(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        for (StorageElement element : input) {
            // Checking content on directories doesn't make sense
            if (!element.isDirectory()) {
                // Check if file contains the search content
                if (element.read().contains(searchKey)) {
                    output.add(element);
                }
            } else {
                // Transparently pass through folders
                output.add(element);
            }
        }
        return output;
    }

    @Override
    public void print() {
        System.out.println("ContentFilter");
    }
}
