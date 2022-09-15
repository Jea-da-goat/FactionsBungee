package com.itndev.factions.SocketConnection.IO;

import com.itndev.factions.SocketConnection.Client.Old.Client;
import com.itndev.factions.SocketConnection.StaticVal;
import com.itndev.factions.Storage.RedisStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                            HashMap<Integer, Object> stream = new HashMap<>();
                            stream.put(StaticVal.getServerNameArgs(), "BungeeCord");
                            stream.put(StaticVal.getDataTypeArgs(), "BungeeCord-Forward");
                            List<String> temp = new ArrayList<>(RedisStorage.TempCommandQueue.stream().toList());
                            stream.put(1, temp);
                            RedisStorage.TempCommandQueue.clear();
                            client.update(stream);
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
