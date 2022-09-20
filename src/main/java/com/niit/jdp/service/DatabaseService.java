/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.service;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
}

//private static final String URL = "jdbc:mysql://localhost:3306/school";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "root";
//
//    private Connection connection;
//
//    public DatabaseService() {
//        this.connection = null;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public boolean connect() throws ClassNotFoundException, SQLException {
//
//        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//        return connection != null;
//    }
//
//
//    public void printConnectionStatus() {
//        if (connection != null) {
//            System.out.println("Database is connected");
//        } else {
//            System.err.println("Database is not connected");
//        }
//    }
//}