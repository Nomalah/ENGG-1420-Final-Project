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

    public ProcessingScenario(String scenarioPath) throws IOException {
        String jsonString = Files.readString(Path.of(scenarioPath));
        this.scenario = new JSONObject(jsonString);
        System.out.println("Input Scenario:");
        System.out.println(jsonString);
    }

    private ArrayList<StorageElement> parseInputStorageEntries(JSONArray inputStorageEntries)
            throws ParseException, JSONException {
        ArrayList<StorageElement> result = new ArrayList<>();
        for (int i = 0; i < inputStorageEntries.length(); i++) {
            JSONObject storageElementJson = inputStorageEntries.getJSONObject(i);
            result.add(StorageElement.create(storageElementJson)); // Create the storage elemnt from JSON info

            // Print some info
            System.out.printf("Added storage element: %s : ", result.get(i).name());
            result.get(i).print();
        }
        return result;
    }

    public void executeScenario() throws ParseException, JSONException {
        ArrayList<StorageElement> currentStorageElements = new ArrayList<>();

        System.out.printf("Executing scenario: %s%n", scenario.getString("name"));

        // Get the list of processing elements
        JSONArray processingElementsJson = scenario.getJSONArray("processing_elements");
        for (int i = 0; i < processingElementsJson.length(); i++) {
            System.out.println("Parsing processing element... ");
            JSONObject processingElementJson = processingElementsJson.getJSONObject(i); // Grab the json for an indiviual processing elementa

            System.out.println("Parsing input entries...");
            JSONArray inputStorageElements = processingElementJson.getJSONArray("input_entries");
            currentStorageElements.addAll(parseInputStorageEntries(inputStorageElements)); // Add any input entries to the queue of storage elements

            System.out.println("Creating processing element...");
            ProcessingElement processingElement = ProcessingElement.create(processingElementJson); // Create the processing element from JSON info

            System.out.print("Executing processing element: ");
            processingElement.print();

            // Update our current queue of storage elements
            currentStorageElements = processingElement.process(currentStorageElements);
        }
    }
}
