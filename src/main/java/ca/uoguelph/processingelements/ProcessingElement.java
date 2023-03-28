package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public interface ProcessingElement {
    ArrayList<StorageElement> process(ArrayList<StorageElement> input);

    void print();

    static ProcessingElement create(JSONObject elementDescription) throws ParseException {
        // Get the parameters of the ProcessingElement and insert them into name/value pairs
        JSONArray parametersJSON = elementDescription.getJSONArray("parameters");
        HashMap<String, String> parameters = new HashMap<>();

        // Insert parameters into a hashmap
        for (int i = 0; i < parametersJSON.length(); i++) {
            JSONObject parameterJSON = parametersJSON.getJSONObject(i);
            parameters.put(parameterJSON.getString("name"), parameterJSON.getString("value"));
        }
        
        // Depending on the type of processing element:
        // Check if the number of parameters are correct,
        // Check if the types are correct,
        // Call the constructor of the coresponding processing element.
        switch (elementDescription.getString("type")) {
            case "NameFilter": {
                if (parameters.size() != 1) {
                    throw new ParseException("Invalid number of parameters for NameFilter", 0);
                }
                String key = parameters.get("Key");
                return new NameFilter(key);
            }
            case "LengthFilter": {
                if (parameters.size() != 2) {
                    throw new ParseException("Invalid number of parameters for LengthFilter", 0);
                }
                long length = Long.parseLong(parameters.get("Length"));
                String operator = parameters.get("Operator");
                return new LengthFilter(length, operator);
            }
            case "ContentFilter": {
                if (parameters.size() != 1) {
                    throw new ParseException("Invalid number of parameters for ContentFilter", 0);
                }
                String key = parameters.get("Key");
                return new ContentFilter(key);
            }
            case "CountFilter": {
                if (parameters.size() != 2) {
                    throw new ParseException("Invalid number of parameters for CountFilter", 0);
                }
                String key = parameters.get("Key");
                int min = Integer.parseInt(parameters.get("Min"));
                return new CountFilter(key, min);
            }
            case "Split": {
                if (parameters.size() != 1) {
                    throw new ParseException("Invalid number of parameters for Split", 0);
                }
                long lines = Long.parseLong(parameters.get("Lines"));
                return new Split(lines);
            }
            case "List": {
                if (parameters.size() != 1) {
                    throw new ParseException("Invalid number of parameters for List", 0);
                }
                long max = Long.parseLong(parameters.get("Max"));
                return new List(max);
            }
            case "Rename": {
                if (parameters.size() != 1) {
                    throw new ParseException("Invalid number of parameters for Rename", 0);
                }
                String suffix = parameters.get("Suffix");
                return new Rename(suffix);
            }
            case "Print":
                return new Print();
            default:
                throw new ParseException("Unknown type of processing element", 0); // Non-existant processing element
        }
    }
}
