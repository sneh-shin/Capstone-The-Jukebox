/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaylistRepository implements SongRepository<Playlist> {

    @Override
    public boolean add(Connection connection, Playlist object) throws SQLException {
        return false;
    }

    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Playlist getById(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }
}
