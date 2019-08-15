package net.octovon;

import net.octovon.commands.*;
import net.octovon.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Octovon extends JavaPlugin {
    public HashMap<Player, Stopwatch> getClocking() {
        return clocking;
    }

    public HashMap<Player, Stopwatch> clocking = new HashMap<>();

    public boolean isClocking(Player player) {
        if(clocking.containsKey(player))
            return true;
        return false;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        getCommand("clockin").setExecutor(new Clockin());
        getCommand("clockout").setExecutor(new Clockout());
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            clocking.remove(player);
        });

        System.out.println("Plugin was force disabled. All players removed from clocking.");
    }

    public String colorText(String input) { return ChatColor.translateAlternateColorCodes('&', input); }
}
