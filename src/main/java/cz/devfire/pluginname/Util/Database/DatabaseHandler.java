package cz.devfire.pluginname.Util.Database;

import cz.devfire.pluginname.Util.Database.Types.DatabaseMysql;
import cz.devfire.pluginname.Util.Database.Types.DatabaseSqlite;
import cz.devfire.pluginname.PluginCore;

import java.io.File;

public class DatabaseHandler {
    private final PluginCore plugin;

    private DatabaseType databaseType;
    private IDatabase database;

    public DatabaseHandler(PluginCore plugin) {
        this.plugin = plugin;
        this.databaseType = DatabaseType.MYSQL;
    }

    public boolean init() {
        this.databaseType = DatabaseType.valueOf(plugin.getConfig().getString("Settings.Database.Type","MYSQL"));

        if (databaseType == DatabaseType.SQLITE) {
            this.database = new DatabaseSqlite(plugin, new File(plugin.getDataFolder() + "/data.db"));
        } else {
            this.database = new DatabaseMysql(plugin,
                    plugin.getConfig().getString("Database.DB"),
                    plugin.getConfig().getString("Database.Host"),
                    plugin.getConfig().getString("Database.User"),
                    plugin.getConfig().getString("Database.Pass"),
                    plugin.getConfig().getInt("Database.Port",3306));
        }

        return connect();
    }

    public boolean connect() {
        if (database.connect()) {
            createTables();

            return true;
        }

        return false;
    }

    public void destroy() {
        if (database.isConnected()) {
            database.disconnect();
        }
    }

    public void createTables() {

    }

    public IDatabase getDatabase() {
        return database;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }
}
