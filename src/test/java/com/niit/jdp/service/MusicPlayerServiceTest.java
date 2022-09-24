package com.niit.jdp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MusicPlayerServiceTest {
    MusicPlayerService musicPlayerService;

    @BeforeEach
    void setUp() {
        musicPlayerService = new MusicPlayerService();
        String filePath = "src/test/resources/songs/Labrinth – Gangster (Official Audio) _ Euphoria (Original Score from the HBO Series).wav";
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void playSuccess() {
        //Assertions.assertEquals();
    }

    @Test
    void playFailure() {

    }
}