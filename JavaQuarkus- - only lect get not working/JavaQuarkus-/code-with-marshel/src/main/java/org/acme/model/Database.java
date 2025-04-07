package org.acme.model;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


@ApplicationScoped
public class Database {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/universitydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234567890-=";

    // Helper method to get a database connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get a connection to the database", e);
        }
    }

}
