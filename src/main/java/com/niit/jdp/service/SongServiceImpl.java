/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.service;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.repository.SongRepositoryImpl;

import java.util.List;

public class SongServiceImpl implements SongService {

    SongRepository songRepository = new SongRepositoryImpl();

    @Override
    public List<Song> getAllSongs() {
        return null;
    }

    @Override
    public List<Song> getAllSongsBasedOnCategory(String category, String name) {
        return null;
    }
}
