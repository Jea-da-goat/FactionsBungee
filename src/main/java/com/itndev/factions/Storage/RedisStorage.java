package com.itndev.factions.Storage;

import com.itndev.factions.Utils.StaticVal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStorage {
    public static final List<String> TempCommandQueue = new ArrayList<>();

    public static void AddCommandToQueue(String command) {
        synchronized (TempCommandQueue) {
            TempCommandQueue.add(command);
        }
    }

    /*public static void AddCommandToQueueFix(String command, String nothing) {
        CmdExecute.updatehashmap(command);
        synchronized (TempCommandQueue) {
            if (!TempCommandQueue.containsKey(StaticVal.getMaxAmount())) {
                TempCommandQueue.put(StaticVal.getMaxAmount(), "1");
                TempCommandQueue.put("1", command);
            } else {
                int num = Integer.parseInt(TempCommandQueue.get(StaticVal.getMaxAmount()));
                TempCommandQueue.put(StaticVal.getMaxAmount(), String.valueOf(num + 1));
                TempCommandQueue.put(String.valueOf(num + 1), command);
            }
        }
    }

    public static void AddBulkCommandToQueue(List<String> BulkCMD) {
        for(String k : BulkCMD) {
            CmdExecute.updatehashmap(k);
        }
        synchronized (TempCommandQueue) {
            for(String command : BulkCMD) {
                if(!TempCommandQueue.containsKey(StaticVal.getMaxAmount())) {
                    TempCommandQueue.put(StaticVal.getMaxAmount(), "1");
                    TempCommandQueue.put("1", command);
                } else {
                    int num = Integer.parseInt(TempCommandQueue.get(StaticVal.getMaxAmount()));
                    TempCommandQueue.put(StaticVal.getMaxAmount(), String.valueOf(num + 1));
                    TempCommandQueue.put(String.valueOf(num + 1), command);
                }
            }
        }
    }*/
}
