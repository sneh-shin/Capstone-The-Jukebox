package com.niit.jdp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DatabaseServiceTest {
    DatabaseService databaseService;
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @BeforeEach
    void setUp() {
        databaseService = new DatabaseService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void connectSuccess() throws SQLException, ClassNotFoundException {
        boolean expected = true;
        boolean actual = databaseService.connect();
        Assertions.assertEquals(true, actual);
    }

    @Test
    void connectFailure() throws SQLException, ClassNotFoundException {
        boolean expected = false;
        boolean actual = databaseService.connect();
        Assertions.assertNotEquals(expected, actual);
    }
}