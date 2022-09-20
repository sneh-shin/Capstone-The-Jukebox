/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.model;

import java.util.Objects;

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

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public double getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(double songDuration) {
        this.songDuration = songDuration;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getSongId() == song.getSongId() && Double.compare(song.getSongDuration(), getSongDuration()) == 0 && Objects.equals(getSongName(), song.getSongName()) && Objects.equals(getArtistName(), song.getArtistName()) && Objects.equals(getGenreName(), song.getGenreName()) && Objects.equals(getAlbumName(), song.getAlbumName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getSongName(), getArtistName(), getGenreName(), getSongDuration(), getAlbumName());
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genreName='" + genreName + '\'' +
                ", songDuration=" + songDuration +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
