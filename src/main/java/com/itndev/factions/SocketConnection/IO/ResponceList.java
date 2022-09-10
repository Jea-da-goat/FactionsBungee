package com.itndev.factions.SocketConnection.IO;

import com.itndev.FaxLib.Utils.Data.DataStream;
import com.itndev.factions.SocketConnection.Client.Client;
import com.itndev.factions.SocketConnection.StaticVal;
import com.itndev.factions.Storage.RedisStorage;
import com.sun.tools.jdi.InternalEventHandler;
import jdk.internal.misc.InnocuousThread;

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
                            DataStream stream = new DataStream("BungeeCord", "BungeeCord-Forward", RedisStorage.TempCommandQueue);
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
