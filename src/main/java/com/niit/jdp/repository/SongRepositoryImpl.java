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

public class SongRepositoryImpl implements SongRepository<Song> {

    @Override
    public boolean add(Connection connection, Song song) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`song`\n" +
                "(`song_id`,\n" +
                "`song_name`,\n" +
                "`artist_name`,\n" +
                "`genre_name`,\n" +
                "`song_duration`,\n" +
                "`album_name`,\n" +
                "`file_path`)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";


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
                //Salesperson salesperson = new Salesperson(salesId, salesPersonName, city, commissionRate);

                Song song = new Song(songId, songName, artistName, genreName, songDuration, albumName, filePath);

                // 7. add the salesperson object to the list
                songsList.add(song);
            }
        }

        return songsList;
    }

    @Override
    public Song getById(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }
}
