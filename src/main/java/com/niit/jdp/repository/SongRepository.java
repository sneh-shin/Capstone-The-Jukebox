/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository<Song> {

    @Override
    public boolean add(Connection connection, Song song) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`song`\n" + "(`song_id`,\n" + "`song_name`,\n" + "`artist_name`,\n" + "`genre_name`,\n" + "`song_duration`,\n" + "`album_name`,\n" + "`file_path`)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, song.getSongId());
            preparedStatement.setString(2, song.getSongName());
            preparedStatement.setString(3, song.getArtistName());
            preparedStatement.setString(4, song.getGenreName());
            preparedStatement.setDouble(5, song.getSongDuration());
            preparedStatement.setString(6, song.getAlbumName());
            preparedStatement.setString(7, song.getFilePath());

            numberOfRowsAffected = preparedStatement.executeUpdate();
        }

        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Song> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`song`;";

        List<Song> songsList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet songResultSet = statement.executeQuery(readQuery);

            while (songResultSet.next()) {
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");

                Song song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);

                songsList.add(song);
            }
        }

        return songsList;
    }

    @Override
    public Song getById(Connection connection, int id) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_id` = ?);";
        Song song = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setInt(1, id);
            ResultSet songResultSet = preparedStatement.executeQuery();

            while (songResultSet.next()) {
                // 6. fetch the values of the current row from the result set
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");
                song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);
            }
        }
        return song;
    }

    public List<Song> getByArtistName(Connection connection, String artistName) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`artist_name` = ?);";
        List<Song> songsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, artistName);
            ResultSet songResultSet = preparedStatement.executeQuery();
            while (songResultSet.next()) {
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");
                Song song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);
                songsList.add(song);
            }
        }
        return songsList;
    }

    public List<String> getAllArtistFromDatabase(Connection connection) throws SQLException {
        String searchQuery = "SELECT `artist_name` FROM `jukebox`.`song`;";

        List<String> artistList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            ResultSet artistResultSet = preparedStatement.executeQuery();
            while (artistResultSet.next()) {
                String artist_name = artistResultSet.getString("artist_name");
                artistList.add(artist_name);
            }
        }
        return artistList;
    }

    public List<Song> getByGenreName(Connection connection, String genreName) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`genre_name` = ?);";

        List<Song> songsList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setString(1, genreName);

            ResultSet songResultSet = preparedStatement.executeQuery();

            while (songResultSet.next()) {
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                genreName = songResultSet.getString("genre_name");
                double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");
                Song song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);
                songsList.add(song);
            }
        }
        return songsList;
    }

    public List<String> getGenreFromDatabase(Connection connection) throws SQLException {
        String searchQuery = "SELECT DISTINCT `genre_name` FROM `jukebox`.`song`;";

        List<String> genreList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            ResultSet genreResultSet = preparedStatement.executeQuery();
            while (genreResultSet.next()) {
                String genre_name = genreResultSet.getString("genre_name");
                genreList.add(genre_name);
            }
        }
        return genreList;
    }
}
