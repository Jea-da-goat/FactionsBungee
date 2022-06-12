package com.itndev.factions;

import com.itndev.factions.Hook.MainHook;
import net.md_5.bungee.api.plugin.Plugin;

public final class FactionsBungee extends Plugin {

    private static FactionsBungee factionsBungee = null;

    public static FactionsBungee getInstance() {
        return factionsBungee;
    }

    @Override
    public void onEnable() {
        factionsBungee = this;

        MainHook.Hook();
    }

    @Override
    public void onDisable() {
        MainHook.unHook();

        factionsBungee = null;
    }
}
