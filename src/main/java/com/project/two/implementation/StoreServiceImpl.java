package com.project.two.implementation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.project.two.interfaces.StoreService;
import com.project.two.server.Server;
import com.project.two.utills.Logger;
import com.project.two.utills.RemoteBusinessException;
import com.project.two.utills.Utills;

public class StoreServiceImpl implements StoreService {

    private Map<String, Object> store = null;
    private Server hostServer = null;
    private Logger logWriter = null;
    private boolean isLogs = false;

    public StoreServiceImpl(Server server) {
        this.configSetup(server, null, null);
    }

    public StoreServiceImpl(Server server, Map<String, Object> store) {
        this.configSetup(server, store, null);
    }

    public StoreServiceImpl(Server server, Map<String, Object> store, Logger logWriter) {
        this.configSetup(server, store, logWriter);
    }

    private void configSetup(Server server, Map<String, Object> store, Logger logWriter) {
        this.store = store == null ? new ConcurrentHashMap<String, Object>() : store;
        this.hostServer = server;
        try {
            this.logWriter = (logWriter != null) ? logWriter : new Logger();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void commonLogs() {
        @SuppressWarnings("deprecation")
        long threadId = Thread.currentThread().getId();
        this.logWriter.logger("Thread :- " + threadId + " is running");
    }

    private void authCheck(String authId) throws RemoteBusinessException {
        if (Utills.isEmptyString(authId)) {
            throw new RemoteBusinessException("Not Authorised");
        }
    }

    /**
     * Put data into the store with the given key and value, subject to authorization checks.
     *
     * @param key    The key to store the data under.
     * @param value  The data to be stored.
     * @param authId The authorization identifier.
     * @throws RemoteException If there's an issue with the remote operation.
     */
    @Override
    public void putData(String key, Object value, String authId) throws RemoteException {
        this.authCheck(authId);
        this.commonLogs();
        String storeKey = this.hostServer.consStoreKeyRe(authId, key);
        if (this.store.containsKey(storeKey)) {
            this.logWriter.logger("Key :- " + storeKey + " already exists in the store");
            throw new RemoteBusinessException("Key :- " + key + " already exists in the store");
        }
        this.store.put(storeKey, value);
    }

    /**
     * Retrieve data from the store using the specified key, subject to authorization checks.
     *
     * @param key    The key to retrieve data for.
     * @param authId The authorization identifier.
     * @return The data associated with the key or null if not found.
     * @throws RemoteException If there's an issue with the remote operation.
     */
    @Override
    public Object getData(String key, String authId) throws RemoteException {
        this.authCheck(authId);
        this.commonLogs();
        String storeKey = this.hostServer.consStoreKeyRe(authId, key);
        if (this.store.containsKey(storeKey)) {
            return this.store.get(storeKey);
        }
        return null;
    }

    /**
     * Delete data from the store using the specified key, subject to authorization checks.
     *
     * @param key    The key to delete data for.
     * @param authId The authorization identifier.
     * @throws RemoteException If there's an issue with the remote operation.
     */
    @Override
    public void deleteData(String key, String authId) throws RemoteException {
        this.authCheck(authId);
        this.commonLogs();
        String storeKey = this.hostServer.consStoreKeyRe(authId, key);
        if (this.store.containsKey(storeKey)) {
            this.store.remove(storeKey);
        }
    }

    /**
     * Stop the server asynchronously, subject to authorization checks.
     *
     * @param authId The authorization identifier.
     * @param force  Whether to forcefully stop the server.
     * @throws RemoteException If there's an issue with the remote operation.
     */
    @Override
    public void stopServer(String authId, boolean force) throws RemoteException {
        this.authCheck(authId);
        this.commonLogs();
        this.hostServer.stopServerAsync(force);
    }

    /**
     * Get the current state of logging in the service.
     *
     * @return true if logging is enabled; false otherwise.
     */
    public boolean isLogs() {
        return isLogs;
    }

    /**
     * Set the logging state of the service.
     *
     * @param isLogs true to enable logging; false to disable.
     */
    public void setLogs(boolean isLogs) {
        this.isLogs = isLogs;
    }

}
