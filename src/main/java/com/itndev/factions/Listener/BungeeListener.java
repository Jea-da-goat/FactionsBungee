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

public class BungeeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p  = e.getPlayer();
        SysUtils.onJoin(p);
        ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(getMessage(p, true)));
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
        ProxyServer.getInstance().getPlayers().forEach(pp -> pp.sendMessage(getMessage(p, false)));
    }

    private BaseComponent getMessage(ProxiedPlayer p, Boolean isjoin) {
        BaseComponent comp = new TextComponent();
        if(isjoin) {
            comp.addExtra(ChatColor.translateAlternateColorCodes('&', "&a&l+ &r&8[&7" + p.getName()) + "&r&8] ");
        } else {
            comp.addExtra(ChatColor.translateAlternateColorCodes('&', "&c&l- &r&8[&7" + p.getName()) + "&r&8] ");
        }
        return comp;
    }

}
