package cz.devfire.pluginname.Util.Files;

import com.google.common.collect.Maps;
import cz.devfire.firelibs.Shared.Utils.StringUtils;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Language {
    private final Plugin plugin;

    private final HashMap<String, String> langMap = Maps.newHashMap();
    private Config langConfig;

    public static Language loadLanguage(Plugin plugin, String pluginPath, String serverPath) {
        Language lang = new Language(plugin,Config.loadConfig(plugin,pluginPath,serverPath));
        lang.load();

        return lang;
    }

    public Language(Plugin plugin, Config langConfig) {
        this.plugin = plugin;
        this.langConfig = langConfig;
    }

    public void reload() {
        langConfig = langConfig.reload();
        load();
    }

    public void load() {
        langMap.clear();

        for (String key : langConfig.getKeys(false)) {
            if (langConfig.isConfigurationSection(key)) {
                for (String subKey : langConfig.getKeys(key)) {
                    langMap.put(key +"."+ subKey, langConfig.getString(key +"."+ subKey));
                }
            } else {
                langMap.put(key, langConfig.getString(key));
            }
        }
    }

    public String get(String key, String... args) {
        return StringUtils.cc(StringUtils.parseArgs(langMap.get(key),args));
    }
}
