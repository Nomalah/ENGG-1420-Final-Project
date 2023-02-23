package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;

public class CountFilter implements ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return byCount(input, "", 5);
    }

    static ArrayList<StorageElement> byCount(ArrayList<StorageElement> input, String search_key, int min_count) {
        return new ArrayList<StorageElement>();
    }
}