package ca.uoguelph.processing_elements;

import ca.uoguelph.storage_elements.StorageElement;
import java.util.ArrayList;
import org.json.JSONArray;
public class List implements ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters) {
        return new ArrayList<StorageElement>();
    }
}
