package me.spectral8420.keepInventoryOnJoin;

import me.spectral8420.keepInventoryOnJoin.config.CustomConfigManager;
import me.spectral8420.keepInventoryOnJoin.misc.Lang;
import me.spectral8420.keepInventoryOnJoin.misc.Settings;
import me.spectral8420.keepInventoryOnJoin.misc.Storage;
import me.spectral8420.keepInventoryOnJoin.helper.ConsoleHelper;
import me.spectral8420.keepInventoryOnJoin.listener.ListenerManager;
import me.spectral8420.keepInventoryOnJoin.task.TaskManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventoryOnJoin extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomConfigManager.register();
        CustomConfigManager.load(this);

        TaskManager.startTask(this);
        ListenerManager.registerListeners(this);

        Lang.getData();
        Settings.load();
        Storage.load();

        ConsoleHelper.log(ChatColor.GREEN + "KeepInventoryOnJoin has been enabled!");
    }

    @Override
    public void onDisable() {
        Settings.save();
        Storage.save();

        CustomConfigManager.save();
        ConsoleHelper.log(ChatColor.RED + "KeepInventoryOnJoin has been disabled!");
    }
}
