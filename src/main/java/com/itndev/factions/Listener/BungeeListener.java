package com.itndev.factions.Listener;

import com.itndev.factions.Utils.SysUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BungeeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p  = e.getPlayer();
        SysUtils.onJoin(p);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnect(ServerConnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        String ServerName = e.getTarget().getName();
        SysUtils.onServerConnect(p, ServerName);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        SysUtils.onLeave(p);
    }

}
