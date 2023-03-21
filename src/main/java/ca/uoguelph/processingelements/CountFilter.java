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
            if (!element.isDirectory()) {
                String[] counted = element.read().split(this.searchKey);
                int count;
                count = counted.length - 1;

                if (count >= minCount) {
                    countOutput.add(element);
                }

            }

            return countOutput;
        }

        @Override
        public void print
        
        
            () {
        System.out.println("CountFilter");
        }
