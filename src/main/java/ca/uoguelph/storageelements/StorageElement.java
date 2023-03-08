package ca.uoguelph.storageelements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import com.laserfiche.repository.api.clients.impl.model.Entry;

public interface StorageElement {
    String name();

    boolean isDirectory();

    String read() throws IOException; // Read entire file verbatim

    long length();

    void rename(String name); // rename file

    ArrayList<StorageElement> getChildStorageElements() throws ParseException;

    void print();

    static StorageElement create(JSONObject elementDescriptionJson) throws JSONException, ParseException {
        switch (elementDescriptionJson.getString("type")) {
            case "local": {
                String filePath = elementDescriptionJson.getString("path");
                return new LocalStorageElement(filePath);
            }
            case "remote": {
                String repoId = elementDescriptionJson.getString("repositoryId");
                int entryId = elementDescriptionJson.getInt("entryId");
                return new RemoteStorageElement(repoId, entryId);
            }
            default:
                throw new ParseException("Unknown type of storage element", 0);
        }
    }
}
