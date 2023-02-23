package ca.uoguelph.storage_elements;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

public interface StorageElement {
    public static StorageElement create(JSONObject element_description) throws JSONException, ParseException {
        switch (element_description.getString("type")){
            case "local":
                String file_path = element_description.getString("path");
                return new LocalStorageElement(file_path);
            case "remote":
                String repo_id = element_description.getString("repositoryId");
                String entry_id = element_description.getString("entryId");
                return new RemoteStorageElement(repo_id, entry_id);
            default:
                throw new ParseException("Unknown type of storage element", 0);
        }
    }

    public String name();
    public boolean isDirectory();
    public String read(); // Read entire file verbatim
    public long length();
    public void rename(String name); // rename file
    public void print();
}
