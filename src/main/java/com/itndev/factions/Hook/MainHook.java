package com.itndev.factions.Hook;

import com.itndev.factions.Commands.ProxyCommands;
import com.itndev.factions.FactionsBungee;
import com.itndev.factions.RedisConnecter.Redis;
import com.itndev.factions.RedisConnecter.StreamIO;

public class MainHook {

    public static void Hook() {
        RegisterListener.run();
        Redis.RedisConnect();
        new StreamIO().Reader();
        FactionsBungee.getInstance().getProxy().getPluginManager().registerCommand(FactionsBungee.getInstance(), new ProxyCommands());
    }

    public static void unHook() {
        Redis.RedisDisConnect();
    }
}
