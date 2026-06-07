package me.spectral8420.keepInventoryOnJoin.listener;

import me.spectral8420.keepInventoryOnJoin.misc.Lang;
import me.spectral8420.keepInventoryOnJoin.misc.Settings;
import me.spectral8420.keepInventoryOnJoin.misc.Storage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Duration;

public class MainListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(player.hasPlayedBefore()) {
            return;
        }

        Storage.getInventoryCountdowns().put(player.getUniqueId(), Settings.getInventoryCountdownInSeconds());

        String formattedTime = Duration.ofSeconds(Settings.getInventoryCountdownInSeconds()).toString().substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();

        player.sendMessage(
                Lang.getMessage("keepInventoryNotifyMessage", true).replace("{0}", formattedTime)
        );

        if(Settings.getFirstJoinSound() != null) {
            player.playSound(player, Settings.getFirstJoinSound(), 1.0F, 1.0F);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(Storage.getInventoryCountdowns().containsKey(player.getUniqueId())) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);

            event.getDrops().clear();
            event.setShouldDropExperience(false);
        }
    }
}
