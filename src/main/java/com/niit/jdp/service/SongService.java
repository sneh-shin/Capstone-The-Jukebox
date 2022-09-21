package com.niit.jdp.service;

import com.niit.jdp.model.Song;

import java.util.List;

public interface SongService {

    public List<Song> getAllSongs();

    public List<Song> getAllSongsBasedOnCategory(String category, String name);
}
