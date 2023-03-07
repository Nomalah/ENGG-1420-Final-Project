package ca.uoguelph.storageelements;

import java.util.ArrayList;
import java.lang.IllegalStateException;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.EntryType;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;

public class RemoteStorageElement implements StorageElement {
    private static AccessKey accessKey = null;
    private static RepositoryApiClient repoClient = null;
    private static EntriesClient entriesClient = null;

    private final String repoId = null;
    private Entry entry;

    public static void initLaserficheClient(String principalServiceKey, String base64AccessKey) throws IllegalStateException {
        if (repoClient != null)
            throw new IllegalStateException("RemoteStorageElement API client has already been initialized");
        accessKey = AccessKey.createFromBase64EncodedAccessKey(base64AccessKey);
        repoClient = RepositoryApiClientImpl.createFromAccessKey(principalServiceKey, accessKey);
        entriesClient = repoClient.getEntriesClient();
    }

    public RemoteStorageElement(String repoId, Integer entryId) {
        if (repoClient == null)
            throw new IllegalStateException("RemoteStorageElement API client has not been initialized");
        repoId = repoId;
        entry = entriesClient.getEntry(repoId, entryId, null).join();
    }

    @Override
    public boolean isDirectory() {
        return entry.getEntryType() == EntryType.FOLDER;
    }

    @Override
    public long length() {
        return 0;
    }

    @Override
    public String name() {
        return entry.getName();
    }

    @Override
    public void rename(String name) {
        entry.name(name);
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
        if (!isDirectory())
            return new ArrayList<>();

        ODataValueContextOfIListOfEntry result = entriesClient
            .getEntryListing(repoId, entry.getId(), true, null, null, null, null, null, "name", null, null, null).join();

        return new ArrayList<>();
    }
}
