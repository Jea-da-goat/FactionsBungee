package com.itndev.factions;

import com.itndev.factions.Hook.MainHook;
import net.md_5.bungee.api.plugin.Plugin;

public final class FactionsBungee extends Plugin {

    private static FactionsBungee factionsBungee = null;

    public static Boolean Shutdown = false;

    public static FactionsBungee getInstance() {
        return factionsBungee;
    }

    @Override
    public void onEnable() {
        factionsBungee = this;

        try {
            MainHook.Hook();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        Shutdown = true;
        MainHook.unHook();

        factionsBungee = null;
    }
}
