package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SongRepositoryTest {
    DatabaseService databaseService;
    SongRepository songRepository;
    List<Song> songList;


    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        songList = new ArrayList<>();
        databaseService.connect();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByArtistNameSuccess() throws SQLException {
        songList = songRepository.getByArtistName(databaseService.getConnection(), "Jungkook");
        Assertions.assertEquals(1, songList.size());
    }

    @Test
    void getByArtistNameFailure() throws SQLException {
        songList = songRepository.getByArtistName(databaseService.getConnection(), "Doja Cat");
        Assertions.assertEquals(0, songList.size());
    }
}