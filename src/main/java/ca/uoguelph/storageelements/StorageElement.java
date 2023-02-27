package ca.uoguelph.storageelements;

import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public interface StorageElement {
    String name();

    boolean isDirectory();

    String read(); // Read entire file verbatim

    long length();

    void rename(String name); // rename file

    void print();

    static StorageElement create(JSONObject elementDescriptionJson) throws JSONException, ParseException {
        switch (elementDescriptionJson.getString("type")) {
            case "local": {
                String filePath = elementDescriptionJson.getString("path");
                return new LocalStorageElement(filePath);
            }
            case "remote": {
                String repoId = elementDescriptionJson.getString("repositoryId");
                String entryId = elementDescriptionJson.getString("entryId");
                return new RemoteStorageElement(repoId, entryId);
            }
            default:
                throw new ParseException("Unknown type of storage element", 0);
        }
    }
}
