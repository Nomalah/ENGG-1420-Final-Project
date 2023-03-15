package ca.uoguelph;

import ca.uoguelph.processingscenario.ProcessingScenario;
import ca.uoguelph.storageelements.RemoteStorageElement;

import java.io.IOException;
import java.text.ParseException;

public final class App {
    private App() {}

    public static void main(String[] args) {
        // Temporary initialize Laserfiche client location
        RemoteStorageElement.initLaserficheClient("replace with service key", "replace with base64 key");

        // Hardcode path
        try {
            System.out.println("Creating Scenario");
            ProcessingScenario scenario = new ProcessingScenario("example.json", true);
            try {
                System.out.println("Executing Scenario");
                scenario.executeScenario();
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Error parsing scenario");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading scenario file");
        }
    }
}
