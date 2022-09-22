/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
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

            }
        }

        return playLists;
    }

    @Override
    public Playlist getById(Connection connection, int id) throws SQLException {

        }
        }

        return playlist;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }
}
