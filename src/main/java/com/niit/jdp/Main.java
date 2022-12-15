package com.niit.jdp;

import com.niit.jdp.exception.PlaylistNotFoundException;
import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.repository.CatalogRepository;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    // This is the main method of the application. It creates an instance of the DatabaseService class and calls the
    // connect method. It then creates an instance of the CatalogRepository class and calls the displayCatalog method.
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        CatalogRepository catalogRepository = new CatalogRepository();
        try {
            databaseService.connect();
            Connection connection = databaseService.getConnection();
            catalogRepository.displayCatalog(connection);
        } catch (SQLException | ClassNotFoundException | SongNotFoundException | PlaylistNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }
}