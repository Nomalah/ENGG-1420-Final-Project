package ca.uoguelph.storage_elements;

public class RemoteStorageElement implements StorageElement {
    public RemoteStorageElement(String repo_id, String entry_id) {

    }

    public boolean isDirectory() {
        return false;
    } 

    public long length() {
        return 0;
    }

    public String name() {
        return "REMOTESTORAGEELEMENT";
    }

    public void rename(String name) {
        
    }

    public String read() {
        return "NOTHING IN FILE - NOT IMPLEMENTED";
    }

    public void print() {

    }
}
