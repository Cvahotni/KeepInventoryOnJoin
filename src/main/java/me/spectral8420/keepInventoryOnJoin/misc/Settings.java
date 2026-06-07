package me.spectral8420.keepInventoryOnJoin.misc;

import me.spectral8420.keepInventoryOnJoin.config.CustomConfig;
import me.spectral8420.keepInventoryOnJoin.config.CustomConfigManager;
import me.spectral8420.keepInventoryOnJoin.helper.ConsoleHelper;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class Settings {
    private static long inventoryCountdownInSeconds = 21600;
    private static Sound firstJoinSound;

    public static void load() {
        CustomConfig config = CustomConfigManager.getConfig("settings");

        if(config.has("inventoryCountdownInSeconds")) {
            inventoryCountdownInSeconds = config.getLong("inventoryCountdownInSeconds");
        }

        if(config.has("firstJoinSound")) {
            if(!config.getString("firstJoinSound").equalsIgnoreCase("")) {
                try {
                    firstJoinSound = Sound.valueOf(config.getString("firstJoinSound"));
                }

                catch (Exception e) {
                    ConsoleHelper.log(ChatColor.RED + "Unable to load first join sound: It is not a valid sound.");
                }
            }
        }
    }

    public static void save() {
        CustomConfig config = CustomConfigManager.getConfig("settings");
        config.set("inventoryCountdownInSeconds", inventoryCountdownInSeconds);
    }

    public static long getInventoryCountdownInSeconds() {
        return inventoryCountdownInSeconds;
    }

    public static Sound getFirstJoinSound() {
        return firstJoinSound;
    }
}
