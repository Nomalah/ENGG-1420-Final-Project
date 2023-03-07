package ca.uoguelph.storageelements;

import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;

public class LocalStorageElement implements StorageElement {

    private String filePath;

    public LocalStorageElement(String filePath) {
        this.filePath = filePath;
        System.out.println(this.isDirectory());
    }

    @Override
    public boolean isDirectory() {
        Path path = (Path) Paths.get(this.filePath);
        if ((Files.exists(path)) == false) {
            return false;
        } else {
            if ((Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) == true) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public long length() {
        return 0;
    }

    @Override
    public String name() {
        return this.filePath;
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
