package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class PlaylistRepositoryTest {
    DatabaseService databaseService;
    PlaylistRepository playlistRepository;
    Playlist playlist;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        playlistRepository = new PlaylistRepository();
        playlist = new Playlist();
        databaseService.connect();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByIdSuccess() throws SQLException {
        playlist = playlistRepository.getById(databaseService.getConnection(), 1);
        Assertions.assertEquals("vibesss", playlist.getPlaylistName());
    }

    @Test
    void getByIdFailure() throws SQLException {
        playlist = playlistRepository.getById(databaseService.getConnection(), 1);
        Assertions.assertNotEquals(null, playlist.getPlaylistName());
    }
}