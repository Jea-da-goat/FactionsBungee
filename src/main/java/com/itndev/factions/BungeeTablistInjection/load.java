package com.itndev.factions.BungeeTablistInjection;

import codecrafter47.bungeetablistplus.api.bungee.BungeeTabListPlusAPI;
import codecrafter47.bungeetablistplus.api.bungee.FakePlayerManager;
import codecrafter47.bungeetablistplus.api.bungee.tablist.FakePlayer;
import com.itndev.factions.FactionsBungee;
import com.itndev.factions.Listener.BungeeListener;
import com.itndev.factions.Storage.RedisStorage;
import com.itndev.factions.Utils.SysUtils;
import net.md_5.bungee.api.config.ServerInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class load {

    private static load mainloader;
    private ServerInfo info = null;
    private HashMap<String, FakePlayer> data = new HashMap<>();
    private HashMap<String, UUID> dataUUID = new HashMap<>();

    public static FakePlayerManager fakePlayerManager;

    public void run() {
        mainloader = this;
    }

    public static load getMainloader() {
        if(mainloader == null) {
            new load().run();
        }
        return mainloader;
    }

    public FakePlayerManager getFakePlayerManager() {
        if(fakePlayerManager == null) {
            fakePlayerManager = BungeeTabListPlusAPI.getFakePlayerManager();
        }
        return fakePlayerManager;
    }

    public void addFakePlayer(String name, UUID uuid, String displayname) {
        new Thread(() -> {
            if(info == null) {
                InetSocketAddress address = new InetSocketAddress(6478);
                info = FactionsBungee.getInstance().getProxy().constructServerInfo("COMPAQ", address, "OBLOCKRECORDS", false);
            }
            FakePlayer fakePlayer = getFakePlayerManager().createFakePlayer(displayname, info);
            fakePlayer.setPing(1);
            fakePlayer.setRandomServerSwitchEnabled(false);
            URL url = null;
            try {
                url = new URL("https://mc-heads.net/avatar/" + name);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedImage image = null;
            try {
                image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fakePlayer.setIcon(BungeeTabListPlusAPI.getIconFromImage(resizeImage(image, 8 ,8)).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            data.put(name, fakePlayer);
            dataUUID.put(name, uuid);
            BungeeListener.join(name, uuid);
            String ANNOUNCE = "PROXY-CONNECTSERVER:=:{" + uuid + "," + name + "," + "DEFAULT_FALLBACK" + "}";
            RedisStorage.AddCommandToQueue(ANNOUNCE);
        }).start();

        //fakePlayer.setSkin(BungeeTabListPlusAPI.getSkinForPlayer("Herobrine"));
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public void removeFakePlayer(String name) {
        if(data.containsKey(name)) {
            fakePlayerManager.removeFakePlayer(data.get(name));
            data.remove(name);
            BungeeListener.leave(name, dataUUID.get(name));
            dataUUID.remove(name);
        }
    }
}
