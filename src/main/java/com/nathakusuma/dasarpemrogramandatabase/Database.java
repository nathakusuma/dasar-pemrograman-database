package com.nathakusuma.dasarpemrogramandatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    private Connection createConnection() {
        System.out.println("Menghubungi Microsoft SQL Server...");

        boolean isProd = Config.getEnv("APP_MODE").equals("production");

        String connectionUrl = String.format(
                "jdbc:sqlserver://%s:%s;" +
                        "databaseName=%s;" +
                        "user=%s;" +
                        "password=%s;" +
                        "encrypt=%b;" +
                        "trustServerCertificate=%b;" +
                        "loginTimeout=30;",
                Config.getEnv("MSSQL_HOST"),
                Config.getEnv("MSSQL_PORT"),
                Config.getEnv("MSSQL_DB"),
                Config.getEnv("MSSQL_USER"),
                Config.getEnv("MSSQL_PASSWORD"),
                isProd,
                !isProd
        );

        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Terhubung dengan Microsoft SQL Server");
            return connection;
        } catch (SQLException e) {
            System.out.println("Gagal menghubungi Microsoft SQL Server: " + e.getMessage());
        }

        return null;
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }

        return connection;
    }
}
