package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;

public class ContentFilter implements ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return byContent(input, "");
    }

    static ArrayList<StorageElement> byContent(ArrayList<StorageElement> input, String search_key) {
        return new ArrayList<StorageElement>();
    }
}