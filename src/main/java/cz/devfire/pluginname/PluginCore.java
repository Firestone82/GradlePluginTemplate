package cz.devfire.pluginname;

import cz.devfire.firelibs.Shared.Utils.StringUtils;
import cz.devfire.pluginname.Util.Files.Config;
import cz.devfire.pluginname.Util.Files.Language;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.logging.Level;

public final class PluginCore extends JavaPlugin {
    private PluginCore plugin;

    private Config config;
    private Language language;

    private boolean debug = false;

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§2Loading..§r");

        Locale.setDefault(new Locale("en","US"));

        config = Config.loadConfig(this,"config.yml","config.yml");
        language = Language.loadLanguage(this,"lang.yml","lang.yml");

        debug = config.getBoolean("Debug");
        if (debug) {
            Bukkit.getConsoleSender().sendMessage("§a - Debug mode is §eenabled§a!");
        } else {
            Bukkit.getConsoleSender().sendMessage("§a - Debug mode is §cdisabled§a!");
        }

        getLogger().info("Enabled plugin with version "+ getDescription().getVersion() +" by "+ StringUtils.stripBrackets(getDescription().getAuthors().toString()));
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled plugin with version "+ getDescription().getVersion() +" by "+ StringUtils.stripBrackets(getDescription().getAuthors().toString()));
    }

    public long reload() {
        long time = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage("[PluginName] Re-Loading..");

        // -------------

        config = config.reload();
        language.reload();

        // -------------

        Bukkit.getConsoleSender().sendMessage("[PluginName] Plugin reloaded in.. "+ (System.currentTimeMillis() - time) +"ms");
        return System.currentTimeMillis() - time;
    }

    // ------

    public void debug(String string) {
        if (debug) {
            Bukkit.getLogger().log(Level.INFO,"DEBUG: "+ string);
        }
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    // ------


    @Override
    public Config getConfig() {
        return config;
    }

    public Language getLanguage() {
        return language;
    }
}
