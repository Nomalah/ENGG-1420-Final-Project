package ca.uoguelph.storageelements;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.params.ParametersForExportDocument;
import com.laserfiche.repository.api.clients.params.ParametersForGetEntry;
import com.laserfiche.repository.api.clients.params.ParametersForGetEntryListing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RemoteStorageElement implements StorageElement {
    public static Boolean initialized = false;
    private static AccessKey accessKey = null;
    private static RepositoryApiClient repoClient = null;
    private static EntriesClient entriesClient = null;

    private final String repoId;
    private final Entry entry;
    
    private String cachedValue = null;

    public RemoteStorageElement(String repoId, Integer entryId) {
        if (!RemoteStorageElement.initialized) {
            throw new IllegalStateException("RemoteStorageElement API client has not been initialized");
        }

        ParametersForGetEntry entryParameters = new ParametersForGetEntry();
        entryParameters = entryParameters.setRepoId(repoId);
        entryParameters = entryParameters.setEntryId(entryId);

        this.repoId = repoId;
        this.entry = entriesClient.getEntry(entryParameters);
    }

    public static void initLaserficheClient(String principalServiceKey, String base64AccessKey) throws IllegalStateException {
        if (RemoteStorageElement.initialized) {
            throw new IllegalStateException("RemoteStorageElement API client has already been initialized");
        }

        accessKey = AccessKey.createFromBase64EncodedAccessKey(base64AccessKey);
        repoClient = RepositoryApiClientImpl.createFromAccessKey(principalServiceKey, accessKey);
        entriesClient = repoClient.getEntriesClient();

        RemoteStorageElement.initialized = true;
    }

    // It's re-used so might as well make a method
    @Override
    public boolean isDirectory() {
        return entry.isContainer();
    }

    @Override
    public long length() {
        if (isDirectory()) // Length of directory must be zero according to document
        {
            return 0;
        }

        return this.read().length();
    }

    @Override
    public String name() {
        return entry.getName();
    }

    @Override
    public void rename(String name) { // Unavailable due to readonly
        System.out.println("Cannot rename remote files");
    }

    @Override
    public String read() {
        // Check if the file is a directory
        if (isDirectory()) {
            return "";
        }
        
        if (this.cachedValue != null) {
            return this.cachedValue;
        }

        // Parameters
        ParametersForExportDocument exportParameters = new ParametersForExportDocument();
        exportParameters.setRepoId(this.repoId);
        exportParameters.setEntryId(this.entry.getId());

        // Input stream
        InputStream stream = null;
        while (stream == null) {
            InputStream temp = null;
            try {
                temp = entriesClient.exportDocumentAsStream(exportParameters);
                stream = temp;
            } catch (ApiException e) {
                System.out.println("Busy wait due to request limit exceeded.");
            }
        }
        

        // Try to read from the stream
        try {
            byte[] content = stream.readAllBytes();
            this.cachedValue = new String(content, StandardCharsets.UTF_8);
            return this.cachedValue;
        } catch(IOException e) {
            return "";
        }
    }

    @Override
    public void print() {
        System.out.printf("REMOTESTORAGEELEMENT - FILENAME: %s - LENGTH: %d - ABSOLUTE PATH: %s - ENTRY ID: %d%n",
                entry.getName(), this.length(),entry.getFullPath(),entry.getId());
    }

    @Override
    public ArrayList<StorageElement> getChildStorageElements() {
        if (!isDirectory()) {
            return new ArrayList<>();
        }

        ParametersForGetEntryListing entryListingParameters = new ParametersForGetEntryListing();
        entryListingParameters = entryListingParameters.setRepoId(this.repoId);
        entryListingParameters = entryListingParameters.setEntryId(this.entry.getId());
        entryListingParameters = entryListingParameters.setOrderby("name");

        ODataValueContextOfIListOfEntry result = entriesClient.getEntryListing(entryListingParameters);

        ArrayList<Entry> entries = new ArrayList<>(result.getValue());
        ArrayList<StorageElement> elements = new ArrayList<>();

        // Convert all the entries to StorageElements and add them to the ArrayList
        for (Entry entry : entries) {
            elements.add(new RemoteStorageElement(this.repoId, entry.getId()));
        }

        return elements;
    }
}
