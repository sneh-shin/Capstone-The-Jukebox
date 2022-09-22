package com.niit.jdp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongRepositoryTest {
    SongRepository songRepository;

    @BeforeEach
    void setUp() {
        songRepository = new SongRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByArtistNameSuccess() {
        //List<Song> list =
    }

    @Test
    void getByArtistNameFailure() {

    }
}