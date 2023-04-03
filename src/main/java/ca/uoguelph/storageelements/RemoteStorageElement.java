package ca.uoguelph.storageelements;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class RemoteStorageElement implements StorageElement {
    static Boolean initialized = false;

    private static AccessKey accessKey = null;
    private static RepositoryApiClient repoClient = null;
    private static EntriesClient entriesClient = null;

    private final String repoId;
    private final Entry entry;

    public RemoteStorageElement(String repoId, Integer entryId) {
        if (repoClient == null) {
            throw new IllegalStateException("RemoteStorageElement API client has not been initialized");
        }
        this.repoId = repoId;
        this.entry = entriesClient.getEntry(repoId, entryId, null).join();
        this.initialized = true;
    }

    public static void initLaserficheClient(String principalServiceKey, String base64AccessKey) throws IllegalStateException {
        if (repoClient != null) {
            throw new IllegalStateException("RemoteStorageElement API client has already been initialized");
        }
        accessKey = AccessKey.createFromBase64EncodedAccessKey(base64AccessKey);
        repoClient = RepositoryApiClientImpl.createFromAccessKey(principalServiceKey, accessKey);
        entriesClient = repoClient.getEntriesClient();
    }

    // It's re-used so might as well make a method
    private File createFileFromEntry() {
        // Stuff taken from Laserfiche unit tests
        Consumer<InputStream> c = inputStream -> {
            File file = new File(entry.getName());
            try (FileOutputStream f = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        break;
                    }
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Export the document as a file
        entriesClient.exportDocument(repoId, entry.getId(), null, c);
        return new File(entry.getName());
    }

    @Override
    public boolean isDirectory() {
        return entry.isContainer();
    }

    @Override
    public long length() {
        if (isDirectory()) // Length of directory must be zero according to document
        {
            return 0;
        }

        // Create a temporary file, and check it's length
        File file = createFileFromEntry();
        long length = file.length();
        file.delete();

        return length;
    }

    @Override
    public String name() {
        return entry.getName();
    }

    @Override
    public void rename(String name) { // Unavailable due to readonly
        System.out.println("Cannot rename remote files");
    }

    @Override
    public String read() {
        if (isDirectory()) {
            return this.name();
        }

        try {
            File file = createFileFromEntry();

            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String currentLine = null;

            // Add each line
            while ((currentLine = reader.readLine()) != null) {
                content.append(currentLine);
                content.append("\n");
            }

            // Delete the cached file
            file.delete();

            // Return the content
            return content.toString();
        } catch (IOException e) {
            return "Could not read file";
        }
    }

    @Override
    public void print() {
        String format = "RemoteStorageElement - %s\n\tEntry ID: %d\n\tAbsolute Path: %s%n";
        System.out.printf(format, entry.getName(), entry.getId(), entry.getFullPath());
    }

    @Override
    public ArrayList<StorageElement> getChildStorageElements() {
        if (!isDirectory()) {
            return new ArrayList<>();
        }

        ODataValueContextOfIListOfEntry result = entriesClient
                .getEntryListing(repoId, entry.getId(), true, null, null, null, null, null, "name", null, null, null).join();

        ArrayList<Entry> entries = new ArrayList<>(result.getValue());
        ArrayList<StorageElement> elements = new ArrayList<>();

        // Convert all the entries to StorageElements and add them to the ArrayList
        for (Entry entry : entries) {
            elements.add(new RemoteStorageElement(this.repoId, entry.getId()));
        }

        return elements;
    }
}
