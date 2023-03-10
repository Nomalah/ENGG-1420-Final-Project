package ca.uoguelph.storageelements;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public interface StorageElement {
    String name();

    boolean isDirectory();

    ArrayList<StorageElement> getChildStorageElements();

    String read(); // Read entire file verbatim

    long length();

    void print();

    static StorageElement create(JSONObject elementDescriptionJson) throws JSONException, ParseException {
        switch (elementDescriptionJson.getString("type")) {
            case "local": {
                String filePath = elementDescriptionJson.getString("path");
                return new LocalStorageElement(filePath);
            }
            case "remote": {
                String repoId = elementDescriptionJson.getString("repositoryId");
                int entryId = Integer.parseInt(elementDescriptionJson.getString("entryId"));
                return new RemoteStorageElement(repoId, entryId);
            }
            default:
                throw new ParseException("Unknown type of storage element", 0);
        }
    }
}
