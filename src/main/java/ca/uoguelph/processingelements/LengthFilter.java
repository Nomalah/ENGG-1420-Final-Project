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
        for (int i = 0; i < input.size(); i++) {
            StorageElement element = input.get(i);
            if (element.isFile()) {
                output.add(element);
            }
            switch (operator) {

            
            case "EQ":
                    if (element.getSize() == Target_length){
                 output.add(entry);
            }
                break;
            
            case "NEQ":
                    if (element.getSize() != Target_length) {
                output.add(entry);
            }
                break;
            
            case "GT":
                    if (element.getSize() < Target_length) {
                output.add(entry);
                break;
           
            case "GTE":
                    if (element.getSize() <= Target_length) {
                output.add(entry);
                break;
                    
            case "LT":
                    if (element.getSize() > Target_length) {
                output.add(entry);
                 break;
                       
            case "LTE":
                    if (element.getSize() >= Target_length) {
                output.add(entry);
                break;

            default:
            System.out.println("The operator does not have a value");
            break;
            }
        }
    return output;
                }

                @Override
                public void print
                    
                
                
                
                    () {
        System.out.println("LengthFilter");
                }
            }
