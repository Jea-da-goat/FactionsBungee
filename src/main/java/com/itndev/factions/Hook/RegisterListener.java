package com.itndev.factions.Hook;

import com.itndev.factions.FactionsBungee;
import com.itndev.factions.Listener.BungeeListener;
import net.md_5.bungee.api.ProxyServer;

public class RegisterListener {

    public static void run() {
        ProxyServer.getInstance().getPluginManager().registerListener(FactionsBungee.getInstance(), new BungeeListener());
    }
}
