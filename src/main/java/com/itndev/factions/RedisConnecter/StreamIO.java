package com.itndev.factions.RedisConnecter;

import com.itndev.factions.Storage.RedisStorage;
import com.itndev.factions.Utils.Read;

import java.util.Collections;
import java.util.Map;

public class StreamIO {

    public void Reader() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                    StreamWriter_OUTPUT();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private String get_Stream_BUNGEE_LINE() {
        return "BUNGEE_PIPELINE";
    }

    private void StreamWriter_OUTPUT() {
        String compressedhashmap;
        /*synchronized (RedisStorage.TempCommandQueue) {
            compressedhashmap = Read.HashMap2String(RedisStorage.TempCommandQueue);
            RedisStorage.TempCommandQueue.clear();
        }
        if(compressedhashmap != null) {
            Map<String, String> body = Collections.singletonMap(StaticVal.getCommand(), compressedhashmap);
            Redis.getRedisCommands().xadd(get_Stream_BUNGEE_LINE(), body);
        }*/
    }
}
