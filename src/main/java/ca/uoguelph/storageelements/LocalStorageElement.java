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
        return this.filePath.toFile().length();
    }

    public Path getFilePath() {
        return filePath;
    }

    @Override
    public String name() {
        return this.filePath.getFileName().toString();
    }

    public void rename(String name) {
        try {
            Files.move(this.filePath, this.filePath.resolveSibling(name));
            this.filePath = this.filePath.resolveSibling(name);
        } catch (IOException ex) {
            System.out.println("Error renaming file");
        }
    }

    @Override
    public String read() {
        try {
            String fileContent = Files.readString(this.filePath);
            return fileContent;
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
        if (!isDirectory()) {
            return new ArrayList<>();
        }
        ArrayList<StorageElement> fileList = new ArrayList<StorageElement>();
        File files[] = this.filePath.toFile().listFiles();
        for(int i = 0; i<files.length; i++){
            LocalStorageElement localStorageElement = new LocalStorageElement(files[i].getAbsolutePath());
            fileList.add(localStorageElement);
        }
        return fileList; // Get all child files/folders
    }
}
