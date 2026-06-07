package me.spectral8420.keepInventoryOnJoin.misc;

import me.spectral8420.keepInventoryOnJoin.config.CustomConfig;
import me.spectral8420.keepInventoryOnJoin.config.CustomConfigManager;

import java.util.*;

public class Storage {
    private static final HashMap<UUID, Long> inventoryCountdowns = new HashMap<>();

    public static void load() {
        CustomConfig config = CustomConfigManager.getConfig("storage");
        List<String> rawInventoryCountdowns = new ArrayList<>();

        if(config.has("inventoryCountdowns")) {
            rawInventoryCountdowns = config.getStringList("inventoryCountdowns");
        }

        for(String rawInventoryCountdown : rawInventoryCountdowns) {
            String[] split = rawInventoryCountdown.split(": ");

            if(split.length != 2) {
                continue;
            }

            inventoryCountdowns.put(UUID.fromString(split[0]), Long.parseLong(split[1]));
        }
    }

    public static void save() {
        CustomConfig config = CustomConfigManager.getConfig("storage");
        List<String> rawInventoryCountdowns = new ArrayList<>();

        for(Map.Entry<UUID, Long> entry : inventoryCountdowns.entrySet()) {
            rawInventoryCountdowns.add(entry.getKey().toString() + ": " + entry.getValue());
        }

        config.set("inventoryCountdowns", rawInventoryCountdowns);
    }

    public static HashMap<UUID, Long> getInventoryCountdowns() {
        return inventoryCountdowns;
    }
}
