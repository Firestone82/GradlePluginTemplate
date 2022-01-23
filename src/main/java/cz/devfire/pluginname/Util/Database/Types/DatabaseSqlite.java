package cz.devfire.pluginname.Util.Database.Types;

import cz.devfire.pluginname.Util.Database.DatabaseType;
import cz.devfire.pluginname.Util.Database.IDatabase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseSqlite implements IDatabase {
    private final Plugin plugin;

    private final File file;
    private final String name;

    private Connection conn;

    public DatabaseSqlite(Plugin plugin, File file) {
        this.plugin = plugin;
        this.file = file;
        this.name = file.getName();
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.SQLITE;
    }

    public Boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ file.getPath());

            Bukkit.getConsoleSender().sendMessage("§a - Connecting sql database "+ name +"... §eSuccessful!");
            return true;
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c - Cannot connect to "+ name +"! Error: " + e.getMessage());
            return false;
        }
    }

    public Boolean disconnect() {
        try {
            conn.close();

            Bukkit.getConsoleSender().sendMessage("§c - Disconnecting sql database "+ name +"... §eSuccessful!");
            return true;
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c - Cannot disconnect from "+ name +"! Error: " + e.getMessage());
            return false;
        }
    }

    public void createIfNotExists() {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean isConnected() {
        Boolean is;

        if (conn == null) {
            is = false;
        } else {
            try {
                if (conn.isClosed()) {
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                is = false;
            }
        }

        return is;
    }

    public Connection getConnection() {
        return conn;
    }

    public void save() {

    }

    public void update(String query) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {
            plugin.getLogger().info(" ");
            Bukkit.getConsoleSender().sendMessage("§c - Query failed! §4" + query);
            Bukkit.getConsoleSender().sendMessage("§c - Error: " + e.getMessage());
        }
    }

    public ResultSet query(String query) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            return ps.executeQuery();
        } catch (Exception e) {
            plugin.getLogger().info(" ");
            Bukkit.getConsoleSender().sendMessage("§c - Query failed! §4" + query);
            Bukkit.getConsoleSender().sendMessage("§c - Error: " + e.getMessage());
        }

        return null;
    }
}
