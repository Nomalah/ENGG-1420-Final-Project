package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;

public class NameFilter implements ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return byName(input, "");
    }

    static ArrayList<StorageElement> byName(ArrayList<StorageElement> input, String search_key) {
        return new ArrayList<StorageElement>();
    }
}