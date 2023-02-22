package ca.uoguelph;

import ca.uoguelph.processing_scenario.ProcessingScenario;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // Hardcode path
        ProcessingScenario scenario = new ProcessingScenario("example.json");
        scenario.executeScenario(true);
    }
}
