package ca.uoguelph.storage_elements;

public class LocalStorageElement implements StorageElement {
    public LocalStorageElement(String file_path) {

    }

    public boolean isDirectory() {
        return false;
    } 

    public long length() {
        return 0;
    }

    public String name() {
        return "LOCALSTORAGEELEMENT";
    }

    public void rename(String name) {
        
    }

    public String read() {
        return "NOTHING IN FILE - NOT IMPLEMENTED";
    }

    public void print() {
        
    }
}
