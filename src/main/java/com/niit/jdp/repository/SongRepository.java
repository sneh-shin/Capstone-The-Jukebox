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

    /**
     * It takes a connection and a song object as parameters, and returns a boolean value indicating whether the song was
     * successfully added to the database
     *
     * @param connection The connection to the database.
     * @param song The song object to be added to the database.
     * @return The number of rows affected by the query.
     */
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

    /**
     * It creates a query to read all the songs from the database, creates a list of songs, executes the query, and adds
     * the songs to the list
     *
     * @param connection the connection to the database
     * @return A list of songs.
     */
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

    /**
     * It takes a connection and an id as parameters, and returns a song object
     *
     * @param connection The connection to the database.
     * @param id The id of the song to be retrieved.
     * @return A song object
     */
    @Override
    public Song getById(Connection connection, int id) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_id` = ?);";
        Song song = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setInt(1, id);
            ResultSet songResultSet = preparedStatement.executeQuery();

            while (songResultSet.next()) {
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

    /**
     * It takes a connection and an artist name as parameters, and returns a list of songs by that artist
     *
     * @param connection Connection object
     * @param artistName The name of the artist whose songs you want to retrieve.
     * @return A list of songs that match the artist name.
     */
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

    /**
     * This function takes a connection to the database and returns a list of all the artists in the database
     *
     * @param connection This is the connection to the database.
     * @return A list of all the artists in the database.
     */
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

    /**
     * It takes a connection and a genre name as parameters, and returns a list of songs that have the same genre name as
     * the one passed as a parameter
     *
     * @param connection the connection to the database
     * @param genreName The name of the genre to search for.
     * @return A list of songs that have the same genre name.
     */
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

    /**
     * This function takes a connection to the database and returns a list of all the genres in the database
     *
     * @param connection The connection to the database.
     * @return A list of all the genres in the database.
     */
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
