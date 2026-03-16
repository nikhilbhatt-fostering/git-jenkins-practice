package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(
                    "jdbc:postgresql://host.containers.internal:5440/cruddb",
                    "postgres",
                    "postgres"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
