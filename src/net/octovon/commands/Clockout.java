package net.octovon.commands;

import net.octovon.Octovon;
import net.octovon.Stopwatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clockout implements CommandExecutor {
    Octovon octovon = Octovon.getPlugin(Octovon.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length > 0) return false;

        if(!(commandSender instanceof Player)) commandSender.sendMessage("Player-only command.");

        Player sender = (Player) commandSender;

        int currentHrs = octovon.getConfig().getInt("players." + sender.getName() + ".hrs");
        int currentMins = octovon.getConfig().getInt("players." + sender.getName() + ".mins");

        try {
            for(Stopwatch stopwatch : octovon.getClocking().values()) {
                if(octovon.getClocking().get(sender).getId() == stopwatch.getId()) {

                    int[] refTime = stopwatch.getTime();

                    octovon.getConfig().set("players." + sender.getName() + ".hrs", currentHrs + refTime[0]);
                    octovon.getConfig().set("players." + sender.getName() + ".mins", currentMins + refTime[1]);
                    octovon.saveConfig();
                }
            }

            octovon.getClocking().remove(sender);

        } catch(IllegalArgumentException ex) {
            commandSender.sendMessage(octovon.colorText("&3&lOctovon &eYou haven't started clock-time. Use /clockin"));
        }

        return true;
    }
}