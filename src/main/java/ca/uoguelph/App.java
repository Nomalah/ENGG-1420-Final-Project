package ca.uoguelph;

import ca.uoguelph.processingscenario.ProcessingScenario;
import ca.uoguelph.storageelements.RemoteStorageElement;

import java.io.IOException;
import java.text.ParseException;

public final class App {
    public static void main(String[] args) {
        // Initialize the laserfiche client before beginning
        RemoteStorageElement.initLaserficheClient("bbCFNpkD2vPdVMvsT-JX", "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiYzdhMThlYWUtNGRhYi00OTk4LTllNzUtN2ZhOTdjNmZlMzdlIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogIlJ6Wkxrbzh2WWE0NEhPYzJ6T0djdld2ckNEYWRTRTJ1WkdTbDlDdmpRSmsiLAoJCSJ4IjogImJvRmJkeVBFbU96OGJ4cXdjaVltNDcwSjdDMXQzMWZhUmRwZ3hkdDNsMXMiLAoJCSJ5IjogIjZUT2M2QW82MFJjNVRyR2RfTXRiN3Q4UnJfM1ZPOWRtOEZyeUtWVllfemsiLAoJCSJkIjogIkVZaklmWUc5bjZyWUhpT1F3ejJHMWZxSG9fVURUc0p5eFFXSnRxNmxLRjQiLAoJCSJpYXQiOiAxNjc3Mjk3ODg4Cgl9Cn0");
        try {
            System.out.println("Creating Scenario");
            // Execute processing scenario from example.json
            ProcessingScenario scenario = new ProcessingScenario("example.json");
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
