/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.model;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private int playlistId;
    private String playlistName;

    private String genreName;

    private List<Song> songList;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, String genreName, List<Song> songList) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.genreName = genreName;
        this.songList = songList;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return getPlaylistId() == playlist.getPlaylistId() && Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getGenreName(), playlist.getGenreName()) && Objects.equals(getSongList(), playlist.getSongList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistId(), getPlaylistName(), getGenreName(), getSongList());
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + getPlaylistId() +
                ", playlistName='" + getPlaylistName() + '\'' +
                ", genreName='" + getGenreName() + '\'' +
                ", songList=" + getSongList() +
                '}';
    }
}
