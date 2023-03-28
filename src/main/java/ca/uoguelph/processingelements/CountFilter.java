package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class CountFilter implements ProcessingElement {

    private final int minCount;
    private final String searchKey;

    public CountFilter(String searchKey, int minCount) {
        this.searchKey = searchKey;
        this.minCount = Math.max(minCount, 0); // Negative counts don't make sense
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        for (StorageElement element : input) {
            // Count filter only makes sense for files
            if (!element.isDirectory()) {
                int numberOfOccurances = element.read().split(this.searchKey).length - 1;

                if (numberOfOccurances >= minCount) {
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
        System.out.println("CountFilter");
    }
}
