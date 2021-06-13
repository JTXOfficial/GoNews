package me.jtx.gonews.commands.subcommands;

import me.jtx.gonews.GoNews;
import me.jtx.gonews.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    private GoNews plugin;

    public Reload(GoNews plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config";
    }

    @Override
    public String getSyntax() {
        return "/gonews reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("gonews.reload")) {
            this.plugin.reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[GoNews]") + ChatColor.GREEN + " Config Reloaded!");
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Message.No-Permission")));
        }
    }
}
