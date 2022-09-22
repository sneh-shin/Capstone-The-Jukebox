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

    private List<String> songList;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, List<String> songList) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
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

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return getPlaylistId() == playlist.getPlaylistId() && Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getSongList(), playlist.getSongList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistId(), getPlaylistName(), getSongList());
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + getPlaylistId() +
                ", playlistName='" + getPlaylistName() + '\'' +
                ", songList=" + getSongList() +
                '}';
    }
}
