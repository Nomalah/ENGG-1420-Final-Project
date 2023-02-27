package ca.uoguelph.storageelements;

public class RemoteStorageElement implements StorageElement {
    public RemoteStorageElement(String repoId, String entryId) {
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
        return "REMOTESTORAGEELEMENT";
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
