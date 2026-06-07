package me.spectral8420.keepInventoryOnJoin.listener;

import me.spectral8420.keepInventoryOnJoin.KeepInventoryOnJoin;
import org.bukkit.Bukkit;

public class ListenerManager {
    public static void registerListeners(KeepInventoryOnJoin plugin) {
        Bukkit.getPluginManager().registerEvents(new MainListener(), plugin);
    }
}
