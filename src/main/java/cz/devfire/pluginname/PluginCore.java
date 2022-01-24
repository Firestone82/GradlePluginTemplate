package cz.devfire.pluginname;

import cz.devfire.firelibs.Shared.Utils.StringUtils;
import cz.devfire.firelibs.Spigot.Database.Objects.IDatabase;
import cz.devfire.firelibs.Spigot.Database.Types.DatabaseMysql;
import cz.devfire.pluginname.Commands.CoreCommand;
import cz.devfire.pluginname.Placeholders.CorePlaceholder;
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

    private CoreCommand coreCommand;
    private CorePlaceholder corePlaceholder;
    private IDatabase coreDatabase;

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

        coreCommand = new CoreCommand(this);

        if (plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            corePlaceholder = new CorePlaceholder(this);
        }

        coreDatabase = new DatabaseMysql(plugin,
                plugin.getConfig().getString("Database.DB"),
                plugin.getConfig().getString("Database.Host"),
                plugin.getConfig().getString("Database.User"),
                plugin.getConfig().getString("Database.Pass"),
                plugin.getConfig().getInt("Database.Port",3306));
        if (coreDatabase.connect()) {
            Bukkit.getConsoleSender().sendMessage("§a - Connecting database "+ coreDatabase.getHost() +"... §eSuccessful!");
        } else {
            Bukkit.getConsoleSender().sendMessage("§a - Connecting database "+ coreDatabase.getHost() +"... §eFailed!");
            Bukkit.getConsoleSender().sendMessage("§c - Plugin cant work without database!");
            Bukkit.getConsoleSender().sendMessage("§c - Shutting down plugin!");
            this.onDisable();
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("Enabled plugin with version "+ getDescription().getVersion() +" by "+ StringUtils.stripBrackets(getDescription().getAuthors().toString()));
    }

    @Override
    public void onDisable() {
        if (coreCommand != null) coreCommand.unregister();
        if (corePlaceholder != null) corePlaceholder.unregister();
        if (coreDatabase != null) coreDatabase.disconnect();

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

    public IDatabase getDatabase() {
        return coreDatabase;
    }
}
