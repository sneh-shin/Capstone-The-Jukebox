/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository<Song> {

    @Override
    public List<Playlist> add(Connection connection, Song song) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`song`\n" + "(`song_id`,\n" + "`song_name`,\n" + "`artist_name`,\n" + "`genre_name`,\n" + "`song_duration`,\n" + "`album_name`,\n" + "`file_path`)\n" + "VALUES (?, ?, ?, ?, ?, ?, ?);";


        // 2. create a statement object
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // 3. set the values of the query parameters
            preparedStatement.setInt(1, song.getSongId());
            preparedStatement.setString(2, song.getSongName());
            preparedStatement.setString(3, song.getArtistName());
            preparedStatement.setString(4, song.getGenreName());
            preparedStatement.setDouble(5, song.getSongDuration());
            preparedStatement.setString(6, song.getAlbumName());
            preparedStatement.setString(7, song.getFilePath());

            // 4. execute the query
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }

        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Song> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`song`;";

        List<Song> songsList = new ArrayList<>();

        // 2. create a statement object
        try (Statement statement = connection.createStatement()) {

            // 3. execute the query
            ResultSet songResultSet = statement.executeQuery(readQuery);

            // 4. iterate over the result set and create a list of salesperson objects
            while (songResultSet.next()) {
                // 5. fetch the values of the current row from the result set
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                Double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");

                // 6. create a salesperson object using the values fetched from the result set
                Song song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);

                // 7. add the salesperson object to the list
                songsList.add(song);
            }
        }

        return songsList;
    }

    @Override
    public Song getById(Connection connection, int id) throws SQLException {
        // 1. write the query for selecting a salesperson object from the `sales_person` table
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_id` = ?);";
        Song song = null;
        // 2. create a statement object
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            // 3. set the values of the query parameters
            preparedStatement.setInt(1, id);

            // 4. execute the query
            ResultSet songResultSet = preparedStatement.executeQuery();

            // 5. check if the result set is empty
            while (songResultSet.next()) {
                // 6. fetch the values of the current row from the result set
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                Double songDuration = songResultSet.getDouble("song_duration");
                String albumName = songResultSet.getString("album_name");
                String filePath = songResultSet.getString("file_path");
                song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);
            }
        }
        return song;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }

    public Song getBySongName(Connection connection, String songName) throws SQLException {
        // 1. write the query for selecting a salesperson object from the `sales_person` table
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_name` = ?);";

        Song song = null;

        // 2. create a statement object
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            // 3. set the values of the query parameters
            preparedStatement.setString(1, songName);

            // 4. execute the query
            ResultSet songResultSet = preparedStatement.executeQuery();

            // 5. check if the result set is empty
            while (songResultSet.next()) {
                // 6. fetch the values of the current row from the result set
                int songId = songResultSet.getInt("song_id");
                songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                String genreName = songResultSet.getString("genre_name");
                Double songDuration = songResultSet.getDouble("song_duration");
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
                Double songDuration = songResultSet.getDouble("song_duration");
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
        // 1. write the query for selecting a salesperson object from the `sales_person` table
        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`genre_name` = ?);";

        List<Song> songsList = new ArrayList<>();

        // 2. create a statement object
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            // 3. set the values of the query parameters
            preparedStatement.setString(1, genreName);

            // 4. execute the query
            ResultSet songResultSet = preparedStatement.executeQuery();

            // 5. check if the result set is empty
            while (songResultSet.next()) {
                // 6. fetch the values of the current row from the result set
                int songId = songResultSet.getInt("song_id");
                String songName = songResultSet.getString("song_name");
                String artistName = songResultSet.getString("artist_name");
                genreName = songResultSet.getString("genre_name");
                Double songDuration = songResultSet.getDouble("song_duration");
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
