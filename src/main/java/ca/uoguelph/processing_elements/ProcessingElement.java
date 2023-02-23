package ca.uoguelph.processing_elements;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.uoguelph.storage_elements.StorageElement;

import java.text.ParseException;
import java.util.ArrayList;

public interface ProcessingElement {
    public ArrayList<StorageElement> process(ArrayList<StorageElement> input);

    public void print();

    public static ProcessingElement create(JSONObject element_description)  throws ParseException {
        JSONArray parameters = element_description.getJSONArray("parameters");
        ArrayList<String> parameter_names = new ArrayList<>();
        ArrayList<String> parameter_values = new ArrayList<>();

        for (int i = 0; i < parameters.length(); i++) {
            JSONObject parameter = parameters.getJSONObject(i);
            parameter_names.add(parameter.getString("name"));
            parameter_values.add(parameter.getString("value"));
        }
        
        switch (element_description.getString("type")){
            case "NameFilter": {
                if (parameter_names.size() != 1) throw new ParseException("Invalid number of parameters for NameFilter", 0);
                String key = parameter_values.get(parameter_names.indexOf("Key"));
                return new NameFilter(key);
            }
            case "LengthFilter": {
                if (parameter_names.size() != 2) throw new ParseException("Invalid number of parameters for LengthFilter", 0);
                long length = Long.parseLong(parameter_values.get(parameter_names.indexOf("Length")));
                String operator = parameter_values.get(parameter_names.indexOf("Operator"));
                return new LengthFilter(length, operator);
            }
            case "ContentFilter": {
                if (parameter_names.size() != 1) throw new ParseException("Invalid number of parameters for ContentFilter", 0);
                String key = parameter_values.get(parameter_names.indexOf("Key"));
                return new ContentFilter(key);
            }
            case "CountFilter": {
                if (parameter_names.size() != 2) throw new ParseException("Invalid number of parameters for CountFilter", 0);
                String key = parameter_values.get(parameter_names.indexOf("Key"));
                int min = Integer.parseInt(parameter_values.get(parameter_names.indexOf("Min")));
                return new CountFilter(key, min);
            }
            case "Split": {
                if (parameter_names.size() != 1) throw new ParseException("Invalid number of parameters for Split", 0);
                long lines = Long.parseLong(parameter_values.get(parameter_names.indexOf("Lines")));
                return new Split(lines);
            }
            case "List": {
                if (parameter_names.size() != 1) throw new ParseException("Invalid number of parameters for List", 0);
                long max = Long.parseLong(parameter_values.get(parameter_names.indexOf("Max")));
                return new List(max);
            }
            case "Rename": {
                if (parameter_names.size() != 1) throw new ParseException("Invalid number of parameters for Rename", 0);
                String suffix = parameter_values.get(parameter_names.indexOf("Suffix"));
                return new Rename(suffix);
            }
            case "Print":
                return new Print();
            default:
                throw new ParseException("Unknown type of processing element", 0);
        }
    }
}
