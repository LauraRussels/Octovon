package net.octovon.events;

import net.octovon.Octovon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    Octovon octovon = Octovon.getPlugin(Octovon.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        if(!octovon.isClocking(event.getPlayer())) {
            return;
        }

        octovon.getClocking().remove(event.getPlayer())
                .stopThread();
    }
}
