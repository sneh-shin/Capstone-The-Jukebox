package com.niit.jdp;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.repository.CatalogRepository;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        CatalogRepository catalogRepository = new CatalogRepository();
        try {
            databaseService.connect();
            Connection connection = databaseService.getConnection();
            catalogRepository.displayCatalog(connection);
        } catch (SQLException | ClassNotFoundException | SongNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }
}