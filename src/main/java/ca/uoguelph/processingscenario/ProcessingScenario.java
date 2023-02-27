package ca.uoguelph.processingscenario;

import ca.uoguelph.processingelements.ProcessingElement;
import ca.uoguelph.storageelements.StorageElement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProcessingScenario {
    private final JSONObject scenario;
    private final boolean verbose;

    public ProcessingScenario(String scenarioPath, boolean verbose) throws IOException {
        String jsonString = Files.readString(Path.of(scenarioPath));
        this.scenario = new JSONObject(jsonString);
        this.verbose = verbose;
        if (this.verbose) {
            System.out.println("Input Scenario:");
            System.out.println(jsonString);
        }
    }

    private ArrayList<StorageElement> parseInputStorageEntries(JSONArray inputStorageEntries)
            throws ParseException, JSONException {
        ArrayList<StorageElement> result = new ArrayList<>();
        for (int i = 0; i < inputStorageEntries.length(); i++) {
            JSONObject storageElementJson = inputStorageEntries.getJSONObject(i);
            result.add(StorageElement.create(storageElementJson));
            if (this.verbose) {
                System.out.printf("Added storage element: %s%n", result.get(i).name());
                result.get(i).print();
            }
        }
        return result;
    }

    public void executeScenario() throws ParseException, JSONException {
        ArrayList<StorageElement> currentElements = new ArrayList<>();
        JSONArray processingElementsJson = scenario.getJSONArray("processing_elements");
        for (int i = 0; i < processingElementsJson.length(); i++) {
            JSONObject processingElementJson = processingElementsJson.getJSONObject(i);
            JSONArray inputStorageElements = processingElementJson.getJSONArray("input_entries");
            currentElements.addAll(parseInputStorageEntries(inputStorageElements)); // Add any input entries to the list
            ProcessingElement processingElement = ProcessingElement.create(processingElementJson);
            if (this.verbose) {
                processingElement.print();
            }
            currentElements = processingElement.process(currentElements);
        }
    }
}
