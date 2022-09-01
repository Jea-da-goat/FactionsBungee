package com.itndev.factions.SocketConnection.IO;

import com.itndev.factions.SocketConnection.Client.Client;
import com.itndev.factions.SocketConnection.StaticVal;
import com.itndev.factions.Storage.RedisStorage;

import java.io.IOException;
import java.util.HashMap;

public class ResponceList {

    private static ResponceList instance = null;

    private Client client = null;

    public static ResponceList get() {
        if(instance == null) {
            instance = new ResponceList();
        }
        return instance;
    }

    public void set(Client client) {
        this.client = client;
    }

    public void disconnect() {
        client.closeAll();
    }

    private ResponceList() {

    }

    public void run() {
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (RedisStorage.TempCommandQueue) {
                        if(!RedisStorage.TempCommandQueue.isEmpty()) {
                            HashMap<String, String> map = new HashMap<>(RedisStorage.TempCommandQueue);
                            RedisStorage.TempCommandQueue.clear();
                            map.put(StaticVal.getServerNameArgs(), "BungeeCord");
                            map.put(StaticVal.getDataTypeArgs(), "BungeeCord-Forward");
                            client.update(map);
                        }
                    }

                    Thread.sleep(100);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
