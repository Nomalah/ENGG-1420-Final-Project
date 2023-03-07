package ca.uoguelph.storageelements;

import java.util.ArrayList;

public class LocalStorageElement implements StorageElement {
    public LocalStorageElement(String filePath) {
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public long length() {
        return 0;
    }

    @Override
    public String name() {
        return "LOCALSTORAGEELEMENT";
    }

    @Override
    public void rename(String name) {
    }

    @Override
    public String read() {
        return "NOTHING IN FILE - NOT IMPLEMENTED";
    }

    @Override
    public void print() {
    }

    @Override
    public ArrayList<StorageElement> getChildStorageElements() {
        if (!isDirectory()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(); // Get all child files/folders
    }
}
