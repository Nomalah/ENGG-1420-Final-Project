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
            if (element.isDirectory()) {
                output.add(element);
            }
            switch (operator) {

            
            Case "EQ":
<<<<<<< Updated upstream
                    if (element.getLength() == operator){
                 output.add(entry);
            }
                    break;
            Case "NEQ":
                    if (element.getLength() != operator) {
=======
                if (Target_length == operator){
                output.add(entry);
            }
                break;
            Case "NEQ":
                if (Target_length != operator) {
>>>>>>> Stashed changes
                output.add(entry);
            }
            break;
            Case "GT":
<<<<<<< Updated upstream
                    if (element.getLength() < operator) {
                output.add(entry);
                break;
                Case "GTE":
                    if (element.getLength() <= operator) {
                    output.add(entry);
                    break;
                    Case "LT":
                    if (element.getLength() > operator) {
                        output.add(entry);
                        break;
                        Case "LTE":
                    if (element.getLength() >= operator) {
                            output.add(entry);
                            break;

                        
                    
                    default:
=======
                if (Target_length < operator) {
                output.add(entry);
                {
                    break;
                    Case "GTE":
                if (Target_length <= operator) {
                        output.add(entry);
                    }
                    break;
                    Case "LT":
                if (Target_length > operator) {
                        output.add(entry);
                    }
                    break;
                    Case "LTE":
                if (Target_length >= operator) {
                        output.add(entry);
                        break;

                    
                
                default:
>>>>>>> Stashed changes
                    System.out.println("The operator does not have a value");
                    break;
            }
        }
    return output;
<<<<<<< Updated upstream
                }

                @Override
                public void print
                    
                
                
                
                    () {
        System.out.println("LengthFilter");
                }
            }
=======
            }

            @Override
            public void print
                    
                    
            
                () {
        System.out.println("LengthFilter");
            }

        }
>>>>>>> Stashed changes
