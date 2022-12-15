/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;


    public DatabaseService() {
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * This function connects to the database and returns true if the connection is successful
     *
     * @return A boolean value.
     */
    public boolean connect() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection != null;
    }
}
