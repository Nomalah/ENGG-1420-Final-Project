package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;

public class Filter implements ProcessingElement {
    ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return new ArrayList<StorageElement>();
    }

    static ArrayList<StorageElement> byName(ArrayList<StorageElement> input, String search_key) {
        return new ArrayList<StorageElement>();
    }
    
    static ArrayList<StorageElement> byLength(ArrayList<StorageElement> input, long length, String operator) {
        return new ArrayList<StorageElement>();
    }

    static ArrayList<StorageElement> byContent(ArrayList<StorageElement> input, String search_key) {
        return Filter.byCount(input, search_key, 1);
    }

    static ArrayList<StorageElement> byCount(ArrayList<StorageElement> input, String search_key, int min_count) {
        return new ArrayList<StorageElement>();
    }
}

public class NameFilter implements ProcessingElement {
    
}
