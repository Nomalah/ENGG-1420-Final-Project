package ca.uoguelph.processing_scenario;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.uoguelph.processing_elements.Filter;
import ca.uoguelph.processing_elements.ProcessingElement;
import ca.uoguelph.storage_elements.StorageElement;
import net.bytebuddy.implementation.bytecode.Throw;

public class ProcessingScenario {
    private JSONObject scenario;
    public ProcessingScenario(String scenario_path) {
        String json_string = Files.readString(Paths.of(scenario_path));
        scenario = new JSONObject(json_string);
    }

    private ArrayList<StorageElement> parseInputEntries(JSONArray input_entries) throws ParseException {
        ArrayList<StorageElement> result = new ArrayList<>();
        for (int i = 0; i < input_entries.length(); i++) {
            JSONObject process_element = input_entries.getJSONObject(i);
            switch (process_element.getString("type")){
                case "local":
                    break;
                case "remote":
                    break;
                default:
                    throw new ParseException("Unknown type of storage element", i);
                }
        }

        return result;
    }

    public void executeScenario(boolean verbose) throws ParseException {
        ArrayList<StorageElement> current_elements = new ArrayList<StorageElement>();
        JSONArray to_process_list = scenario.getJSONArray("processing_elements");
        for (int i = 0; i < to_process_list.length(); i++) {
            JSONObject process_element_json = to_process_list.getJSONObject(i);
            JSONArray input_entries = process_element_json.getJSONArray("input_entries");
            current_elements.addAll(parseInputEntries(input_entries));
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
                    break;
                case "List":
                    break;
                case "Rename":
                    break;
                case "Print":
                    break;
                default:
                    throw new ParseException("Unknown type of processing element", i);
            }
            JSONA  paramet
            current_elements = processing_element.process(current_elements, input_entries);
        }
    }
}
