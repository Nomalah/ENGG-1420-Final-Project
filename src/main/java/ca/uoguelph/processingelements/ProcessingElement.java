package ca.uoguelph.processingelements;

import ca.uoguelph.storageelements.StorageElement;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public interface ProcessingElement {
    ArrayList<StorageElement> process(ArrayList<StorageElement> input);

    void print();

    static ProcessingElement create(JSONObject elementDescription) throws ParseException {
        JSONArray parameters = elementDescription.getJSONArray("parameters");
        ArrayList<String> parameterNames = new ArrayList<>();
        ArrayList<String> parameterValues = new ArrayList<>();

        for (int i = 0; i < parameters.length(); i++) {
            JSONObject parameter = parameters.getJSONObject(i);
            parameterNames.add(parameter.getString("name"));
            parameterValues.add(parameter.getString("value"));
        }

        switch (elementDescription.getString("type")) {
            case "NameFilter": {
                if (parameterNames.size() != 1) {
                    throw new ParseException("Invalid number of parameters for NameFilter", 0);
                }
                String key = parameterValues.get(parameterNames.indexOf("Key"));
                return new NameFilter(key);
            }
            case "LengthFilter": {
                if (parameterNames.size() != 2) {
                    throw new ParseException("Invalid number of parameters for LengthFilter", 0);
                }
                long length = Long.parseLong(parameterValues.get(parameterNames.indexOf("Length")));
                String operator = parameterValues.get(parameterNames.indexOf("Operator"));
                return new LengthFilter(length, operator);
            }
            case "ContentFilter": {
                if (parameterNames.size() != 1) {
                    throw new ParseException("Invalid number of parameters for ContentFilter", 0);
                }
                String key = parameterValues.get(parameterNames.indexOf("Key"));
                return new ContentFilter(key);
            }
            case "CountFilter": {
                if (parameterNames.size() != 2) {
                    throw new ParseException("Invalid number of parameters for CountFilter", 0);
                }
                String key = parameterValues.get(parameterNames.indexOf("Key"));
                int min = Integer.parseInt(parameterValues.get(parameterNames.indexOf("Min")));
                return new CountFilter(key, min);
            }
            case "Split": {
                if (parameterNames.size() != 1) {
                    throw new ParseException("Invalid number of parameters for Split", 0);
                }
                long lines = Long.parseLong(parameterValues.get(parameterNames.indexOf("Lines")));
                return new Split(lines);
            }
            case "List": {
                if (parameterNames.size() != 1) {
                    throw new ParseException("Invalid number of parameters for List", 0);
                }
                long max = Long.parseLong(parameterValues.get(parameterNames.indexOf("Max")));
                return new List(max);
            }
            case "Rename": {
                if (parameterNames.size() != 1) {
                    throw new ParseException("Invalid number of parameters for Rename", 0);
                }
                String suffix = parameterValues.get(parameterNames.indexOf("Suffix"));
                return new Rename(suffix);
            }
            case "Print":
                return new Print();
            default:
                throw new ParseException("Unknown type of processing element", 0);
        }
    }
}
