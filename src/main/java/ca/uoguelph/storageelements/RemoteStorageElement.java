package ca.uoguelph.storageelements;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class RemoteStorageElement implements StorageElement {
    private static AccessKey accessKey = null;
    private static RepositoryApiClient repoClient = null;
    private static EntriesClient entriesClient = null;

    private final String repoId;
    private final Entry entry;

    public RemoteStorageElement(String repoId, Integer entryId) {
        if (repoClient == null)
            throw new IllegalStateException("RemoteStorageElement API client has not been initialized");
        this.repoId = repoId;
        this.entry = entriesClient.getEntry(repoId, entryId, null).join();
    }

    public static void initLaserficheClient(String principalServiceKey, String base64AccessKey) throws IllegalStateException {
        if (repoClient != null)
            throw new IllegalStateException("RemoteStorageElement API client has already been initialized");
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
                    if (length <= 0)
                        break;
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        entriesClient.exportDocument(repoId, entry.getId(), null, c);
        return new File(entry.getName());
    }

    @Override
    public boolean isDirectory() {
        return entry.isContainer();
    }

    @Override
    public long length() {
        // Stuff taken from Laserfiche unit tests
        File file = createFileFromEntry();
        return file.length();
    }

    @Override
    public String name() {
        return entry.getName();
    }

    @Override
    public void rename(String name) {
        entry.name(name);
    }

    @Override
    public String read() throws IOException {
        File file = createFileFromEntry();

        // Read the file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String currentLine = null;

        while ((currentLine = reader.readLine()) != null)
            content.append(currentLine + "\n");

        // Delete the cached file
        file.delete();

        // Return the content
        return content.toString();
    }

    @Override
    public void print() {
        String format = "%s\n\tEntry ID: %d\n\tAbsolute Path: %s";
        System.out.printf((format) + "%n", entry.getName(), entry.getId(), entry.getFullPath());
    }

    @Override
    public ArrayList<StorageElement> getChildStorageElements() throws ParseException {
        if (!isDirectory())
            return new ArrayList<>();

        ODataValueContextOfIListOfEntry result = entriesClient
            .getEntryListing(repoId, entry.getId(), true, null, null, null, null, null, "name", null, null, null).join();

        ArrayList<Entry> entries = new ArrayList<>(result.getValue());
        ArrayList<StorageElement> elements = new ArrayList<>();

        // Convert all the entries to StorageElements and add them to the ArrayList
        String jsonFormat = "\"{\"type\": \"remote\", \"repositoryId\": \"%s\", \"entryId\": \"%d\"}\"";
        for (Entry entry : entries) {
            JSONObject elementJSON = (JSONObject) JSONObject.stringToValue(jsonFormat.formatted(repoId, entry.getId()));
            elements.add(StorageElement.create(elementJSON));
        }

        return elements;
    }
}
