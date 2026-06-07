package me.spectral8420.keepInventoryOnJoin.config;

import me.spectral8420.keepInventoryOnJoin.KeepInventoryOnJoin;

import java.util.HashMap;

public class CustomConfigManager {
    private static final HashMap<String, CustomConfig> configs = new HashMap<>();

    public static void register() {
        configs.put("lang", new CustomConfig("lang", true));
        configs.put("settings", new CustomConfig("settings", true));
        configs.put("storage", new CustomConfig("storage", true));
    }

    public static void load(KeepInventoryOnJoin plugin) {
        for(String key : configs.keySet()) {
            configs.get(key).load(plugin);
        }
    }

    public static void save() {
        for(String key : configs.keySet()) {
            CustomConfig config = configs.get(key);

            if(config.isSaveOnDisable()) {
                config.save();
            }
        }
    }

    public static void reload(KeepInventoryOnJoin plugin) {
        load(plugin);
        save();
    }

    public static CustomConfig getConfig(String name) {
        return configs.get(name);
    }
}