/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.model;

import java.util.Objects;

public class Song {

    private final int songId;
    private final String songName;
    private final String artistName;
    private final String genreName;
    private final double songDuration;
    private final String albumName;

    private final String filePath;

    public Song(int songId, String songName, String artistName, String genreName, double songDuration, String albumName, String filePath) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.genreName = genreName;
        this.songDuration = songDuration;
        this.albumName = albumName;
        this.filePath = filePath;
    }

    public int getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenreName() {
        return genreName;
    }

    public double getSongDuration() {
        return songDuration;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getSongId() == song.getSongId() && Double.compare(song.getSongDuration(), getSongDuration()) == 0 && Objects.equals(getSongName(), song.getSongName()) && Objects.equals(getArtistName(), song.getArtistName()) && Objects.equals(getGenreName(), song.getGenreName()) && Objects.equals(getAlbumName(), song.getAlbumName()) && Objects.equals(getFilePath(), song.getFilePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getSongName(), getArtistName(), getGenreName(), getSongDuration(), getAlbumName(), getFilePath());
    }

    @Override
    public String toString() {
        return "Song{" + "songId=" + getSongId() + ", songName='" + getSongName() + '\'' + ", artistName='" + getArtistName() + '\'' + ", genreName='" + getGenreName() + '\'' + ", songDuration=" + getSongDuration() + ", albumName='" + getAlbumName() + '\'' + ", filePath='" + getFilePath() + '\'' + '}';
    }
}

