package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class NameFilter implements ProcessingElement {
    private String searchKey;
    public NameFilter(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        
        this.searchKey = searchKey.toUpperCase();
        
        for (int i = 0; i < input.size(); i++){
            if (input.get(i).name().contains(this.searchKey)) {
                output.add(input.get(i));
            }
        }
        return output;
    }

    @Override
    public void print() {
        System.out.println("NameFilter");
    }
}
