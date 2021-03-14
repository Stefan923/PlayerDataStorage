package me.stefan923.playerdatastorage.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private final String username;
    private final String password;
    private final String tablePrefix;
    private final String url;

    private Connection connection;

    public MySQLConnection(String tablePrefix, String host, int port, String databaseName, String username, String password) {
        this.username = username;
        this.password = password;
        this.tablePrefix = tablePrefix;
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?characterEncoding=latin1&useConfigs=maxPerformance";
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || !this.connection.isValid(5)) {
            this.connect();
        }

        return this.connection;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
