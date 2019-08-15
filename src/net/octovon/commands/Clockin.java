package net.octovon.commands;

import net.octovon.Octovon;
import net.octovon.Stopwatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clockin implements CommandExecutor {
    Octovon octovon = Octovon.getPlugin(Octovon.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length > 0) return false;

        if(!(commandSender instanceof Player)) commandSender.sendMessage("Player-only command.");

        try {
            octovon.getClocking().put((Player) commandSender, new Stopwatch())
                    .startThread();
        } catch(NullPointerException ex) {
            System.out.println("Null at startThread()");
        }

        return true;
    }
}
