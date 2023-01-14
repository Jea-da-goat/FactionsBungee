package com.itndev.factions.Listener;

import com.itndev.factions.FactionsBungee;
import com.itndev.factions.Utils.SysUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.awt.*;
import java.util.UUID;

public class BungeeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p  = e.getPlayer();
        join(p.getName(), p.getUniqueId());
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
        leave(p.getName(), p.getUniqueId());
    }

    public static void leave(String name, UUID uuid) {
        SysUtils.onLeave(name, uuid);
        ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(getMessage(name, false)));
    }

    public static void join(String name, UUID uuid) {
        SysUtils.onJoin(name, uuid);
        ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(getMessage(name, true)));
    }

    private static BaseComponent getMessage(String name, Boolean isjoin) {
        BaseComponent comp = new TextComponent();
        if(isjoin) {
            comp.addExtra(ChatColor.translateAlternateColorCodes('&', "&r&8[&a&l+&r&8] &7" + name));
        } else {
            comp.addExtra(ChatColor.translateAlternateColorCodes('&', "&r&8[&c&l-&r&8] &7" + name));
        }
        return comp;
    }

}
