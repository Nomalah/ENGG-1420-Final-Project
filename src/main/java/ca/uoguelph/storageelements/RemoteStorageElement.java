package ca.uoguelph.storageelements;

import java.util.ArrayList;
import java.lang.IllegalStateException;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;

public class RemoteStorageElement implements StorageElement {
    private static AccessKey accessKey = null;
    private static RepositoryApiClient client = null;
    public static void initLaserfisheClient(String pricipalServiceKey, String base64AccessKey) throws IllegalStateException {
        if (client != null || accessKey != null) {
            throw new IllegalStateException("RemoteStorageElement API client has already been initialized");
        }
        accessKey = AccessKey.createFromBase64EncodedAccessKey(base64AccessKey);
        client = RepositoryApiClientImpl.createFromAccessKey(pricipalServiceKey, accessKey);
    }

    public RemoteStorageElement(String repoId, Integer entryId) {
        if (client == null || accessKey == null) {
            throw new IllegalStateException("RemoteStorageElement API client has not been initialized");
        }
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

    @Override
    public ArrayList<StorageElement> getChildStorageElements() {
        if (!isDirectory()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(); // Get all child files/folders
    }
}
