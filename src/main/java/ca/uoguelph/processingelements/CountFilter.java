package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class CountFilter implements ProcessingElement {

    private final int minCount;
    private final String searchKey;

    public CountFilter(String searchKey, int minCount) {

        this.searchKey = searchKey;
        minCount = Math.max(minCount, 0);
        this.minCount = minCount;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> countOutput = new ArrayList<>();
        for (StorageElement element : input) {
            if (!(element.isDirectory()) && countOccurances(element.read(), searchKey) >= minCount) {
                countOutput.add(element);
            }

        }
        return countOutput;
    }

    @Override
    public void print() {
        System.out.println("CountFilter");
    }

    private int countOccurances(String name, String searchKey) {
    int count = 0;
    
    
    count ++
    return count;
}
