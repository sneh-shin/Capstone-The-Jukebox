package com.niit.jdp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MusicPlayerServiceTest {
    MusicPlayerService musicPlayerService;

    @BeforeEach
    void setUp() {
        musicPlayerService = new MusicPlayerService();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void playSuccess() {
        musicPlayerService.setSongPath("src/test/resources/songs/Labrinth – Gangster (Official Audio) _ Euphoria (Original Score from the HBO Series).wav");
        musicPlayerService.play();
        Assertions.assertTrue(musicPlayerService.isSongStatus());
    }

    @Test
    void playFailure() {
        musicPlayerService.setSongPath(null);
        //musicPlayerService.play();
        Assertions.assertThrows(RuntimeException.class, () -> musicPlayerService.play());
    }
}