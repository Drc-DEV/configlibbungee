package pro.dracarys.configlibbungee.config;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pro.dracarys.configlibbungee.ConfigLib;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public abstract class CustomFile implements ICustomFile {

    private Configuration config;
    private File file;
    private File configFile;

    public CustomFile(String parent) {
        Plugin instance = ConfigLib.getPlugin();
        if (!instance.getDataFolder().exists())
            instance.getDataFolder().mkdir();

        if (parent != null) {
            file = new File(instance.getDataFolder(), File.separator + parent);
            if (!file.exists()) {
                file.mkdir();
            }
            configFile = new File(file, getName() + ".yml");
        } else {
            configFile = new File(instance.getDataFolder(), getName() + ".yml");
        }
        try {
            if (configFile.createNewFile()) {
                instance.getLogger().log(Level.INFO, "Creating Config file " + getName() + ".yml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();
    }

    public void reloadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public Configuration getConfig() {
        return config;
    }

}
