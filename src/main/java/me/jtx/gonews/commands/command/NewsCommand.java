package me.jtx.gonews.commands.command;

import me.jtx.gonews.GoNews;
import me.jtx.gonews.utils.BookUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsCommand implements CommandExecutor {

    private GoNews plugin;

    public NewsCommand(GoNews plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                List<String> pages = new ArrayList<>();
                TextComponent firstText = new TextComponent("");
                firstText.addExtra("\n" + ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("book.message").replaceAll("%n%", "\n")));
                pages.add(ComponentSerializer.toString(firstText));
                BookUtils.openBook(player, pages);
            }
        }
        return false;
    }
}
