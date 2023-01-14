package com.itndev.factions.Commands;

import com.itndev.factions.BungeeTablistInjection.load;
import com.itndev.factions.Utils.SysUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class ProxyCommands extends Command {
    public ProxyCommands() {
        super("factionsbungee", "factionsbungee.admin", "fb");
    }

    @Deprecated
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("factionsbungee.admin")) {
            //
            if(args[0].equalsIgnoreCase("refresh")) {
                SysUtils.reloadList();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lINFO &7리프레시 완료"));
            } else if(args[0].equalsIgnoreCase("addfplayer")) {
                load.getMainloader().addFakePlayer(args[1], UUID.fromString(args[2]), SysUtils.Args2String(args, 3));
            } else if(args[0].equalsIgnoreCase("removefplayer")) {
                load.getMainloader().removeFakePlayer(args[1]);
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR &r&7권한이 없습니다"));
        }
    }
}
