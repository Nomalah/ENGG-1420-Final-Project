package ca.uoguelph;

import java.io.IOException;
import java.text.ParseException;

import ca.uoguelph.processing_scenario.ProcessingScenario;

public final class App {
    public static void main(String[] args) {
        // Hardcode path
        try {
            System.out.println("Creating Scenario");
            ProcessingScenario scenario = new ProcessingScenario("example.json");
            try {
                System.out.println("Executing Scenario");
                scenario.executeScenario(true);
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
