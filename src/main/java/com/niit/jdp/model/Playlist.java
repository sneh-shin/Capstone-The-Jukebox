/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.model;

import java.util.List;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private List<Song> songList;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, List<Song> songList) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songList = songList;
    }
}
