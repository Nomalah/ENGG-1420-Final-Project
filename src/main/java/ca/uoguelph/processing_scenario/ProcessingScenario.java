package ca.uoguelph.processing_scenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.uoguelph.processing_elements.ProcessingElement;
import ca.uoguelph.storage_elements.StorageElement;

public class ProcessingScenario {
    private JSONObject scenario;
    private boolean verbose;
    public ProcessingScenario(String scenario_path, boolean verbose) throws IOException {
        String json_string = Files.readString(Path.of(scenario_path));
        scenario = new JSONObject(json_string);
        this.verbose = verbose;
        if (this.verbose) {
            System.out.println("Input Scenario:");
            System.out.println(json_string);
        }
    }

    private ArrayList<StorageElement> parseInputEntries(JSONArray input_entries) throws ParseException, JSONException {
        ArrayList<StorageElement> result = new ArrayList<>();
        for (int i = 0; i < input_entries.length(); i++) {
            JSONObject process_element = input_entries.getJSONObject(i);
            result.add(StorageElement.create(process_element));
            if (this.verbose){
                System.out.printf("Added storage element: %s%n", result.get(i).name());
                result.get(i).print();
            }
        }
        return result;
    }

    public void executeScenario() throws ParseException, JSONException {
        ArrayList<StorageElement> current_elements = new ArrayList<>();
        JSONArray to_process_list = scenario.getJSONArray("processing_elements");
        for (int i = 0; i < to_process_list.length(); i++) {
            JSONObject process_element_json = to_process_list.getJSONObject(i);
            JSONArray input_entries = process_element_json.getJSONArray("input_entries");
            current_elements.addAll(parseInputEntries(input_entries)); // Add any input entries to the list
            ProcessingElement processing_element = ProcessingElement.create(process_element_json);
            if (this.verbose) {
                processing_element.print();
            }
            current_elements = processing_element.process(current_elements);
        }
    }
}
