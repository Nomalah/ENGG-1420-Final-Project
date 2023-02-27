package ca.uoguelph.storageelements;

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
}
