package me.quexer.homeserverv2.proxysystem;

import com.google.gson.Gson;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class ProxySystem extends Plugin {


    private static ProxySystem instance;
    private static String prefix;
    private static ProxyServer bungeeCord;
    private static Gson gson;

    @Override
    public void onEnable() {
       init();
    }

    private void init() {
        setInstance(this);
        setPrefix("§8» §6§lProxy §8➜ ");
        setBungeeCord(getProxy());
        setGson(new Gson());
    }


    public static ProxySystem getInstance() {
        return instance;
    }

    public static void setInstance(ProxySystem instance) {
        ProxySystem.instance = instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        ProxySystem.prefix = prefix;
    }

    public static ProxyServer getBungeeCord() {
        return bungeeCord;
    }

    public static void setBungeeCord(ProxyServer bungeeCord) {
        ProxySystem.bungeeCord = bungeeCord;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        ProxySystem.gson = gson;
    }
}
