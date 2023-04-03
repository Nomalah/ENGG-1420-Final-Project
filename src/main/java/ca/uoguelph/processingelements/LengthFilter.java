package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.util.ArrayList;

public class LengthFilter implements ProcessingElement {

    private final long targetLength;
    private final String operator;

    public LengthFilter(long Target_length, String operator) {
        this.targetLength = Target_length;
        this.operator = operator;
    }

    @Override
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input) {
        ArrayList<StorageElement> output = new ArrayList<>();
        for (StorageElement element : input) {
            // Check length against target length based on operator
            switch (operator) {
                case "EQ":
                    if (element.length() == targetLength) {
                        output.add(element);
                    }
                    break;
                case "NEQ":
                    if (element.length() != targetLength) {
                        output.add(element);
                    }
                    break;
                case "GT":
                    if (element.length() > targetLength) {
                        output.add(element);
                    }
                    break;
                case "GTE":
                    if (element.length() >= targetLength) {
                        output.add(element);
                    }
                    break;
                case "LT":
                    if (element.length() < targetLength) {
                        output.add(element);
                    }
                    break;
                case "LTE":
                    if (element.length() <= targetLength) {
                        output.add(element);
                    }
                    break;
                default:
                    System.out.println("The operator is invalid!");
                    break;
            }
        }

        return output;
    }

    @Override
    public void print() {
        System.out.println("LengthFilter");
    }
}
