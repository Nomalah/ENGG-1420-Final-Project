package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class List implements ProcessingElement {

    private long maxFolderEntries;

    public List(long max) {
        this.maxFolderEntries = max;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isDirectory()) {
                ArrayList<StorageElement> childElements = input.get(i).getChildStorageElements();

                if (maxFolderEntries < childElements.size()) {
                    for (int j = 0; j < maxFolderEntries; j++) {
                        output.add(childElements.get(j));
                    }
                } else {
                    for (int j = 0; j < childElements.size(); j++) {
                        output.add(childElements.get(j));
                    }
                }

            } else {
                output.add(input.get(i)); //if its a file pass it through element

            }
        }
        return output;
    }

    @Override
    public void print() {
        System.out.println("List");
    }
}
