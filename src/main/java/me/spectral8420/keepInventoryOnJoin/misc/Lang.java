package me.spectral8420.keepInventoryOnJoin.misc;

import me.spectral8420.keepInventoryOnJoin.config.CustomConfig;
import me.spectral8420.keepInventoryOnJoin.config.CustomConfigManager;
import me.spectral8420.keepInventoryOnJoin.helper.ConsoleHelper;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class Lang {
    private static final HashMap<String, String> messages = new HashMap<>();

    public static void getData() {
        CustomConfig langConfig = CustomConfigManager.getConfig("lang");

        addMessage("prefix", langConfig);
        addMessage("keepInventoryMessage", langConfig);
        addMessage("keepInventoryNotifyMessage", langConfig);
        addMessage("keepInventoryExpiredMessage", langConfig);
    }

    public static void addMessage(String key, CustomConfig langConfig) {
        try {
            messages.put(key, langConfig.getString(key));
        }

        catch (Exception e) {
            ConsoleHelper.log(ChatColor.RED + "Failed to load message from lang file due to an exception: " + e);
        }
    }

    public static String getMessage(String key, boolean includePrefix) {
        if(!messages.containsKey(key)) return key;
        return includePrefix ? messages.get("prefix") + messages.get(key) : messages.get(key);
    }
}
