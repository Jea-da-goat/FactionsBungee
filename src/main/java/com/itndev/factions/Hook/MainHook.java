package com.itndev.factions.Hook;

import com.itndev.factions.Commands.ProxyCommands;
import com.itndev.factions.FactionsBungee;
import com.itndev.factions.RedisConnecter.Redis;
import com.itndev.factions.RedisConnecter.StreamIO;
import com.itndev.factions.SocketConnection.Main;

public class MainHook {

    public static void Hook() throws Exception {
        RegisterListener.run();
        Main.launch();
        //Redis.RedisConnect();
        //new StreamIO().Reader();
        FactionsBungee.getInstance().getProxy().getPluginManager().registerCommand(FactionsBungee.getInstance(), new ProxyCommands());
    }

    public static void unHook() {
        //Redis.RedisDisConnect();
        Main.disconnect();
    }
}
