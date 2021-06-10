package me.jtx.gonews.events;

import me.jtx.gonews.GoNews;
import me.jtx.gonews.utils.BookUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class OnJoinEvent implements Listener {

    private GoNews plugin;

    public OnJoinEvent(GoNews plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        List<String> pages = new ArrayList<>();

        TextComponent firstText = new TextComponent("\n" + ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("GoNews.Message").replaceAll("%n%", "\n")));
        pages.add(ComponentSerializer.toString(firstText));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                BookUtils.openBook(player, pages);
            }
        }, 1L);
    }
}
