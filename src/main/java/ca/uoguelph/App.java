package ca.uoguelph;

import ca.uoguelph.processingscenario.ProcessingScenario;
import java.io.IOException;
import java.text.ParseException;

public final class App {
    private App() {}

    public static void main(String[] args) {
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
