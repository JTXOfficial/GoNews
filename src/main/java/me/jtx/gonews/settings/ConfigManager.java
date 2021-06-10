package me.jtx.gonews.settings;

import me.jtx.gonews.GoNews;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    protected GoNews goNews;

    protected File file;

    protected FileConfiguration config;

    public ConfigManager(GoNews goNews, String FileName) {
        this.goNews = goNews;
        this.file = new File(goNews.getDataFolder() + "/" + FileName);
        if (!this.file.exists())
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            try {
                this.config.load(this.file);
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
