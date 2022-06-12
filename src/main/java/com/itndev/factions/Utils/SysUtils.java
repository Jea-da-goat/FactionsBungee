package com.itndev.factions.Utils;

import com.itndev.factions.Storage.RedisStorage;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class SysUtils {
    public static void onJoin(ProxiedPlayer player) {
        //add player
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        String ANNOUNCE = "PROXY-JOIN:=:{" + uuid + "," + name + "}";
        RedisStorage.AddCommandToQueue(ANNOUNCE);
    }

    public static void onLeave(ProxiedPlayer player) {
        String name = player.getName();
        UUID uuid = player.getUniqueId();
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
}
