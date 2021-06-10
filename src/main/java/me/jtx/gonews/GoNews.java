package me.jtx.gonews;

import me.jtx.gonews.commands.SubCommandManager;
import me.jtx.gonews.events.OnJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GoNews extends JavaPlugin {

    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[GoNews] is now enabled");

        registerEvent();
        registerCommands();
        config();
        registerConfig();
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[GoNews] is now disabled");
    }

    public void registerCommands() {
        getCommand("gonews").setExecutor(new SubCommandManager());
    }

    public void registerEvent() {
        getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
    }

    private void config() {
        getConfig().addDefault("GoNews.Message", "&6&lMy Message %n%&c&lIs cool");

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void registerConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

}
