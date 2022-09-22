/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository implements Repository<Playlist> {

    @Override
    public boolean add(Connection connection, Playlist playlist) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`playlist`\n" + "(`playlist_id`,\n" + "`playlist_name`,\n" + "`song_list`)\n" + "VALUES (?, ?, ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, playlist.getPlaylistId());
            preparedStatement.setString(2, playlist.getPlaylistName());
            String songList = playlist.getSongList().toString().trim().replaceAll("\\[\\]", "");
            preparedStatement.setString(3, songList);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`playlist`;";

        List<Playlist> playLists = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet playlistResultSet = statement.executeQuery(readQuery);
            while (playlistResultSet.next()) {
                int playlistId = playlistResultSet.getInt("playlist_id");
                String playlistName = playlistResultSet.getString("playlist_name");
                List<String> songList = List.of(playlistResultSet.getString("song_list").split(","));
                Playlist playlist = new Playlist(playlistId, playlistName, songList);
                playLists.add(playlist);

            }
        }

        return playLists;
    }

    @Override
    public Playlist getById(Connection connection, int id) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`playlist` WHERE `playlist_id` = ?;";
        Playlist playlist = null;
        try (PreparedStatement statement = connection.prepareStatement(readQuery)) {
            statement.setInt(1, id);
            ResultSet playlistResultSet = statement.executeQuery();
            while (playlistResultSet.next()) {
                // 5. fetch the values of the current row from the result set
                int playlistId = playlistResultSet.getInt("playlist_id");
                String playlistName = playlistResultSet.getString("playlist_name");
                List<String> songList = List.of(playlistResultSet.getString("song_list").split(","));
                playlist = new Playlist(playlistId, playlistName, songList);

            }
        }

        return playlist;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }
}
