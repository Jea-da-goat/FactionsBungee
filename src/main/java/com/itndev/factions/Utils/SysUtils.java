package com.itndev.factions.Utils;

import com.itndev.factions.Storage.RedisStorage;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class SysUtils {
    public static void onJoin(String name, UUID uuid) {
        //add player
        String ANNOUNCE = "PROXY-JOIN:=:{" + CommonUtils.String2Byte(uuid.toString()) + " " + CommonUtils.String2Byte(name) + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void onLeave(String name, UUID uuid) {

        String ANNOUNCE = "PROXY-LEAVE:=:{" + CommonUtils.String2Byte(uuid.toString()) + " " + CommonUtils.String2Byte(name) + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void reloadList() {
        refreshStorage();
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            onJoinNoAnnounce(player);
        }
    }

    public static void refreshStorage() {
        RedisStorage.AddCommandToQueue("PROXY-REFRESH:=:REFRESH");
    }

    public static void onJoinNoAnnounce(ProxiedPlayer player) {
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        String ANNOUNCE = "PROXY-PLAYERINFO:=:{" + CommonUtils.String2Byte(uuid.toString()) + " " + CommonUtils.String2Byte(name) + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void onServerConnect(ProxiedPlayer player, String ServerName) {
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        String ANNOUNCE = "PROXY-CONNECTSERVER:=:{" + CommonUtils.String2Byte(uuid.toString()) + " " + CommonUtils.String2Byte(name) + " " + CommonUtils.String2Byte(ServerName) + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static String Args2String(String[] args, int Start) {
        String FinalString = "";
        for(int k = Start; k < args.length; k++) {
            if(args[k] == null) {
                break;
            }
            if(k == args.length - 1) {
                FinalString = FinalString + args[k];
            } else {
                FinalString = FinalString + args[k] + " ";
            }

        }
        return FinalString;
    }

    public static ArrayList string2list(String k) {
        if(k.contains(" ")) {
            String[] parts = k.split(" ");
            ArrayList<String> memlist = new ArrayList<>();
            for(String d : parts) {
                memlist.add(CommonUtils.Byte2String(d));
            }
            return memlist;
        } else {
            ArrayList<String> memlist = new ArrayList<>();
            memlist.add(CommonUtils.Byte2String(k));
            return memlist;
        }
    }
    public static String list2string(ArrayList<String> list) {
        String k = "";
        int i = 0;
        for(String c : list) {
            i = i + 1;
            if(list.size() > i) {
                k = k + CommonUtils.String2Byte(c) + " ";
            } else {
                k = k + CommonUtils.String2Byte(c);
            }


        }
        return k;
    }
}
