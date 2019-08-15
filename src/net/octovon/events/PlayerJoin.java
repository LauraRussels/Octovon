package net.octovon.events;

import net.octovon.Octovon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    Octovon octovon = Octovon.getPlugin(Octovon.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();

        octovon.getConfig().getConfigurationSection("players").getKeys(true).
                forEach(player -> {
                    if(!player.contains(".")) {
                        if(!joiner.getName().equals(player)) {
                            octovon.getConfig().set("players." + joiner.getName() + ".hrs", 0);
                            octovon.getConfig().set("players." + joiner.getName() + ".mins", 0);
                            octovon.saveConfig();

                            System.out.println("New player setup for clocking: " + joiner.getName());
                        }
                    }
        });

    }
}
