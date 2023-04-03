package ca.uoguelph.storageelements;

import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;

public class LocalStorageElement implements StorageElement {

    private Path filePath;

    public LocalStorageElement(String filePath) {
        this.filePath = Paths.get(filePath);
        if (!Files.exists(this.filePath)) {
            System.out.println("Error file doesn't exist");
        }
    }

    @Override
    public boolean isDirectory() {
        return Files.isDirectory(this.filePath, LinkOption.NOFOLLOW_LINKS);
    }

    @Override
    public long length() {
        return this.filePath.toFile().length(); // Length of Directory is equal to 0
    }

    public Path getFilePath() {
        return filePath;
    }

    @Override
    public String name() {
        return this.filePath.getFileName().toString();
    }

    @Override
    public void rename(String name) {
        try {
            // This renames the file, and then changes the file path that we refer to, in accordance with the new file
            Files.move(this.filePath, this.filePath.resolveSibling(name));
            this.filePath = this.filePath.resolveSibling(name);
        } catch (IOException ex) {
            System.out.println("Error renaming file");
        }
    }

    @Override
    public String read() {
        try {
            return Files.readString(this.filePath); // Return the file as a string
        } catch (IOException ex) {
            return "Error reading file";
        }
    }

    @Override
    public void print() {
        System.out.print("LOCALSTORAGEELEMENT - FILENAME: " + this.name());
        System.out.print(" - LENGTH: " + this.length());
        System.out.println(" - ABSOLUTE PATH: " + this.filePath.toAbsolutePath());
    }

    @Override
    public ArrayList<StorageElement> getChildStorageElements() {
        // Files don't have any child elements
        if (!isDirectory()) {
            return new ArrayList<>();
        }
        ArrayList<StorageElement> childStorageElements = new ArrayList<>();
        File files[] = this.filePath.toFile().listFiles();
        for (File childFile : files) {
            // Convert known files into localstoragelements
            childStorageElements.add(new LocalStorageElement(childFile.getAbsolutePath()));
        }
        return childStorageElements; // Get all child files/folders
    }
}
