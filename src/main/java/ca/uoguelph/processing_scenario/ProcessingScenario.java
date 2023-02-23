package ca.uoguelph.processing_scenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.uoguelph.processing_elements.NameFilter;
import ca.uoguelph.processing_elements.CountFilter;
import ca.uoguelph.processing_elements.ContentFilter;
import ca.uoguelph.processing_elements.LengthFilter;
import ca.uoguelph.processing_elements.Print;
import ca.uoguelph.processing_elements.ProcessingElement;
import ca.uoguelph.processing_elements.Rename;
import ca.uoguelph.processing_elements.List;
import ca.uoguelph.processing_elements.Split;
import ca.uoguelph.storage_elements.StorageElement;

public class ProcessingScenario {
    private JSONObject scenario;
    public ProcessingScenario(String scenario_path) throws IOException {
        String json_string = Files.readString(Path.of(scenario_path));
        scenario = new JSONObject(json_string);
    }

    private ArrayList<StorageElement> parseInputEntries(JSONArray input_entries) throws ParseException, JSONException {
        ArrayList<StorageElement> result = new ArrayList<>();
        for (int i = 0; i < input_entries.length(); i++) {
            JSONObject process_element = input_entries.getJSONObject(i);
            result.add(StorageElement.create(process_element));
        }

        return result;
    }

    public void executeScenario(boolean verbose) throws ParseException, JSONException {
        ArrayList<StorageElement> current_elements = new ArrayList<StorageElement>();
        JSONArray to_process_list = scenario.getJSONArray("processing_elements");
        for (int i = 0; i < to_process_list.length(); i++) {
            JSONObject process_element_json = to_process_list.getJSONObject(i);
            JSONArray input_entries = process_element_json.getJSONArray("input_entries");
            current_elements.addAll(parseInputEntries(input_entries)); // Add any input entries to the list
            ProcessingElement processing_element;
            switch (process_element_json.getString("type")){
                case "NameFilter":
                    processing_element = new NameFilter();
                    break;
                case "LengthFilter":
                    processing_element = new LengthFilter();
                    break;
                case "ContentFilter":
                    processing_element = new ContentFilter();
                    break;
                case "CountFilter":
                    processing_element = new CountFilter();
                    break;
                case "Split":
                    processing_element = new Split();
                    break;
                case "List":
                    processing_element = new List();
                    break;
                case "Rename":
                    processing_element = new Rename();
                    break;
                case "Print":
                    processing_element = new Print();
                    break;
                default:
                    throw new ParseException("Unknown type of processing element", i);
            }
            current_elements = processing_element.process(current_elements, input_entries);
        }
    }
}
