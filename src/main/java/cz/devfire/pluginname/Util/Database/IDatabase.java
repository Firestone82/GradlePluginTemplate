package cz.devfire.pluginname.Util.Database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDatabase {

    Boolean connect();

    Boolean disconnect();

    Boolean isConnected();

    Connection getConnection();

    void update(String query);

    ResultSet query(String query);

    DatabaseType getType();

}
