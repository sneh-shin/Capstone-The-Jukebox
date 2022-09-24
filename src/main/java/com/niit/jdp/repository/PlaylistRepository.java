/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository implements Repository<Playlist> {
    SongRepository songRepository;
    Playlist playlist;

    List<Playlist> playlistList;


    public PlaylistRepository() {
        songRepository = new SongRepository();
        playlistList = new ArrayList<>();
    }

    @Override
    public boolean add(Connection connection, Playlist playlist) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`playlist` (`playlist_name`, `song_list`) VALUES (?, ?);";
        int numberOfRowsAffected;
        String songList = playlist.getSongList().toString().trim().replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playlist.getPlaylistName());
            preparedStatement.setString(2, songList);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`playlist`;";
        //List<String> listSong = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Song> list = songRepository.getAll(connection);
        while (resultSet.next()) {
            playlist = new Playlist();
            playlist.setPlaylistId(resultSet.getInt("playlist_id"));
            playlist.setPlaylistName(resultSet.getString("playlist_name"));
            List<String> songList = List.of(resultSet.getString("song_list").split(","));
            playlist.setSongList(songList);
            playlistList.add(playlist);
        }
        return playlistList;
    }

    @Override
    public Playlist getById(Connection connection, int id) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`playlist` WHERE `playlist_id` = ?;";
        Playlist playlist = null;
        try (PreparedStatement statement = connection.prepareStatement(readQuery)) {
            statement.setInt(1, id);
            ResultSet playlistResultSet = statement.executeQuery();
            while (playlistResultSet.next()) {
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
