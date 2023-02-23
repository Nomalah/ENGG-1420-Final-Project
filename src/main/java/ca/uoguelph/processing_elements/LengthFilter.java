package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;

public class LengthFilter implements ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return byLength(input, 1000, "GTE");
    }

    static ArrayList<StorageElement> byLength(ArrayList<StorageElement> input, long length, String operator) {
        return new ArrayList<StorageElement>();
    }
}