package ca.uoguelph.processing_elements;

import org.json.JSONArray;

import ca.uoguelph.storage_elements.StorageElement;

import java.util.ArrayList;

public interface ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input, JSONArray parameters);
}
