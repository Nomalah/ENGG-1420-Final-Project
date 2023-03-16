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
            switch (operator) {

            
            case "EQ":
                    if (element.getLength() == Target_length){
                 output.add(entry);
            }
                break;
            
            case "NEQ":
                    if (element.getLength() != Target_length) {
                output.add(entry);
            }
                break;
            
            case "GT":
                    if (element.getLength() < Target_length) {
                output.add(entry);
            }
                break;
           
            case "GTE":
                    if (element.getLength() <= Target_length) {
                output.add(entry);
            }
                break;
                    
            case "LT":
                    if (element.getLength() > Target_length) {
                output.add(entry);
            }
                 break;
                       
            case "LTE":
                    if (element.getLength() >= Target_length) {
                output.add(entry);
            }
                break;

            default:
            System.out.println("The operator does not have a value");
            break;
            }
        }
            if (element.isDirectory()) {
                break;
            }
    return output;
                }

                
                
                    () {
        System.out.println("LengthFilter");
                }
            
