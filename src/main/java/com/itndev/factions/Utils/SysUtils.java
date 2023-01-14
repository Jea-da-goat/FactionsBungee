package com.itndev.factions.Utils;

import com.itndev.factions.Storage.RedisStorage;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class SysUtils {
    public static void onJoin(String name, UUID uuid) {
        //add player
        String ANNOUNCE = "PROXY-JOIN:=:{" + uuid + "," + name + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void onLeave(String name, UUID uuid) {

        String ANNOUNCE = "PROXY-LEAVE:=:{" + uuid + "," + name + "}";
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
        String ANNOUNCE = "PROXY-PLAYERINFO:=:{" + uuid + "," + name + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void onServerConnect(ProxiedPlayer player, String ServerName) {
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        String ANNOUNCE = "PROXY-CONNECTSERVER:=:{" + uuid + "," + name + "," + ServerName + "}";
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
}
