package pro.dracarys.configlibbungee;

import net.md_5.bungee.api.plugin.Plugin;
import pro.dracarys.configlibbungee.config.CustomFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfigLib {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(Plugin pl) {
        plugin = pl;
    }

    private static Map<String, CustomFile> fileMap = new HashMap<>();

    public static void addFile(CustomFile file) {
        fileMap.put(file.getName(), file);
        file.init();
    }

    public static Map<String, CustomFile> getFileMap() {
        return fileMap;
    }

    public static CustomFile getFile(String fileName) {
        if (fileMap.containsKey(fileName))
            return fileMap.get(fileName);
        return null;
    }

    public static void init(String fileName) {
        if (fileMap.containsKey(fileName))
            fileMap.get(fileName).init();
    }

    public static void initAll(String... excluded) {
        for (CustomFile file : fileMap.values()) {
            if (excluded != null && Arrays.stream(excluded).anyMatch(s -> file.getName().equalsIgnoreCase(s))) continue;
            file.init();
        }
    }

}
