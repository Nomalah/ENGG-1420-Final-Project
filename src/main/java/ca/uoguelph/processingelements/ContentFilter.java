package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class ContentFilter implements ProcessingElement {

    private String searchKey;

    public ContentFilter(String searchKey) {
        this.searchKey = searchKey;
    }
    
    @Override

    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> contentOutput = new ArrayList<>();
        for (StorageElement element : input) {
            if (!(element.isDirectory()) && element.read().contains(searchKey)) {
                contentOutput.add(element);
            }
        }
        return contentOutput;
    }

    @Override
    public void print() {
        System.out.println("ContentFilter");
    }
}
