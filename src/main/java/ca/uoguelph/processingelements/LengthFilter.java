package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class LengthFilter implements ProcessingElement {

    private final long Target_length;
    private final String operator;

    public LengthFilter(long Target_length, String operator) {
        this.Target_length = Target_length;
        this.operator = operator;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        for (int i=0; i<input.size();i++){
            StorageElement element = input.get(i);
            if(element.isDirectory()){
                 output.add(element);
            }
           
        }
        return output;
    }

    @Override
    public void print() {
        System.out.println("LengthFilter");
    }
}
