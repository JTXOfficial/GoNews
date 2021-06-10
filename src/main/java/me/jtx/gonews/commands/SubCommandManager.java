package me.jtx.gonews.commands;

import me.jtx.gonews.GoNews;
import me.jtx.gonews.commands.subcommands.Reload;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SubCommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public SubCommandManager() {
        subCommands.add(new Reload(GoNews.getPlugin(GoNews.class)));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        getSubCommands().get(i).perform(player, args);
                    }
                }
            } else if (args.length == 0 || args.length > 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015&8[ &6GoNews &8]&7\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015"));
                for (int i = 0; i < getSubCommands().size(); i++) {
                    player.sendMessage(" " + " " +ChatColor.translateAlternateColorCodes('&', "&6") + getSubCommands().get(i).getSyntax() + ChatColor.translateAlternateColorCodes('&', "&8") + " - " + ChatColor.translateAlternateColorCodes('&', "&a") + getSubCommands().get(i).getDescription());
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015&8[ &6GoNews &8]&7\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015\u2015"));
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
