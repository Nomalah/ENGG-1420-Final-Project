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
            {
                switch (operator) {

                    case "EQ":
                        if (element.length() == Target_length) {
                            output.add(element);
                        }
                        break;

                    case "NEQ":
                        if (element.length() != Target_length) {
                            output.add(element);
                        }
                        break;

                    case "GT":
                        if (element.length() < Target_length) {
                            output.add(element);
                        }
                        break;

                    case "GTE":
                        if (element.length() <= Target_length) {
                            output.add(element);
                        }
                        break;

                    case "LT":
                        if (element.length() > Target_length) {
                            output.add(element);
                        }
                        break;

                    case "LTE":
                        if (element.length() >= Target_length) {
                            output.add(element);
                        }
                        break;

                    default:
                        System.out.println("The operator does not have a value");
                        break;
                }
            }
            else if (element.isDirectory()) {
                break;
            }
            return output;
        }
        void print
        
        
        
        
            () { 
            System.out.println("LengthFilter");
        }
