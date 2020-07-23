package com.kuntsevich.task8.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDb {

    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("datares.database");
        Properties properties = new Properties();
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String password = resource.getString("db.password");
        String autoReconnect = resource.getString("db.autoReconnect");
        String encoding = resource.getString("db.encoding");
        String useUnicode = resource.getString("db.useUnicode");
        properties.put("user", user);
        properties.put("password", password);
        properties.put("autoReconnect", autoReconnect);
        properties.put("characterEncoding", encoding);
        properties.put("useUnicode", useUnicode);
        return DriverManager.getConnection(url, properties);
    }
}
