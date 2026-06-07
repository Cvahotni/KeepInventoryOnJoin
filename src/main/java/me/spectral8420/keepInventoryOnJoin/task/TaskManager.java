package me.spectral8420.keepInventoryOnJoin.task;

import me.spectral8420.keepInventoryOnJoin.KeepInventoryOnJoin;
import me.spectral8420.keepInventoryOnJoin.misc.Lang;
import me.spectral8420.keepInventoryOnJoin.misc.Storage;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TaskManager {
    public static void startTask(KeepInventoryOnJoin plugin) {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            List<UUID> toRemove = new ArrayList<>();

            for(Map.Entry<UUID, Long> entry : Storage.getInventoryCountdowns().entrySet()) {
                Player player = plugin.getServer().getPlayer(entry.getKey());

                if(player == null) {
                    continue;
                }

                if(entry.getValue() > 0.0) {
                    String formattedTime = Duration.ofSeconds(entry.getValue()).toString().substring(2)
                            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                            .toLowerCase();

                    player.sendActionBar(Component.text(
                            Lang.getMessage("keepInventoryMessage", true).replace("{0}", formattedTime)
                    ));

                    entry.setValue(entry.getValue() - 1);
                }

                else {
                    player.sendActionBar(Component.text(
                            Lang.getMessage("keepInventoryExpiredMessage", true)
                    ));

                    player.sendMessage(Lang.getMessage("keepInventoryExpiredMessage", true));
                    player.playSound(player, Sound.BLOCK_BELL_USE, 1.0F, 1.0F);

                    toRemove.add(entry.getKey());
                }
            }

            for(UUID uuid : toRemove) {
                Storage.getInventoryCountdowns().remove(uuid);
            }
        }, 0L, 20L);
    }
}
