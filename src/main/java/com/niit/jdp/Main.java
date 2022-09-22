package com.niit.jdp;

import com.niit.jdp.repository.CatalogRepository;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseService();
        try {
            databaseService.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection connection = databaseService.getConnection();
        CatalogRepository catalogRepository = new CatalogRepository();
        try {
            catalogRepository.displayCatalog(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}