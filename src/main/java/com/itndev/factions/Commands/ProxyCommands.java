package com.itndev.factions.Commands;

import com.itndev.factions.Utils.SysUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyCommands extends Command {
    public ProxyCommands() {
        super("factionsbungee", "factionsbungee.admin", new String[] { "fb"});
    }

    @Deprecated
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        if(sender.hasPermission("factionsbungee.admin")) {
            //
            if(args[1].equalsIgnoreCase("refresh")) {
                SysUtils.reloadList();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lINFO &7리프레시 완료"));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR &r&7권한이 없습니다"));
        }
    }
}
