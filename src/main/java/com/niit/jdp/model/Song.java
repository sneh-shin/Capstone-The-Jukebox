/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.model;

public class Song {

    private int songId;
    private String songName;
    private String artistName;
    private String genreName;
    private double songDuration;
    private String albumName;

    public Song() {
    }

    public Song(int songId, String songName, String artistName, String genreName, double songDuration, String albumName) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.genreName = genreName;
        this.songDuration = songDuration;
        this.albumName = albumName;
    }
}
