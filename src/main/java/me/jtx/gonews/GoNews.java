package me.jtx.gonews;

import me.jtx.gonews.commands.SubCommandManager;
import me.jtx.gonews.commands.command.NewsCommand;
import me.jtx.gonews.events.OnJoinEvent;
import me.jtx.gonews.settings.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class GoNews extends JavaPlugin {

    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[GoNews] is now enabled");
        checkUpdate();
        registerEvent();
        registerCommands();
        registerConfig();
        config();
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[GoNews] is now disabled");
    }

    public void registerCommands() {
        getCommand("gonews").setExecutor(new SubCommandManager());
        getCommand("news").setExecutor(new NewsCommand(this));
    }

    public void registerEvent() {
        getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
    }

    private void config() {
        getConfig().set("Check-Update.enable", true);
        getConfig().set("Open-Book-Onjoin.enable", true);
        getConfig().set("Open-Book-Onjoin.delay", 1);
        getConfig().set("Message.No-Permission", "&cYou don't have permission to do this!");
        getConfig().set("book.message", "&6&lMy Message %n%&c&lIs cool");
    }

    public void registerConfig() {
        saveDefaultConfig();
    }

    private void checkUpdate() {
        if (this.getConfig().getBoolean("Check-Update.enable")) {
            Logger logger = this.getLogger();
            new UpdateChecker(this, 93152).getVersion(version -> {
                if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    logger.info("There is not a new update available.");
                } else {
                    logger.info("There is a new update available.");
                }
            });
        }
    }
}
