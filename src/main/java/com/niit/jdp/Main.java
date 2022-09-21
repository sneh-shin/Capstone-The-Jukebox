package com.niit.jdp;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepositoryImpl;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MusicPlayerService musicPlayerService = new MusicPlayerService();
        // musicPlayerService.play("src/main/resources/songs/Labrinth – Gangster (Official Audio) _ Euphoria (Original Score from the HBO Series).wav");
        //Song song1 = new Song(1,"Dandelions","Ruth B","Pop",2.30,"Abc","src/main/resources/songs/Ruth B. - Dandelions (Lyrics) (Slowed + Reverb).wav");
        //Song song2 = new Song(2,"2","Ruth B1","Pop1",2.30,"Abc1","src/main/resources/songs/Labrinth – Gangster (Official Audio) _ Euphoria (Original Score from the HBO Series).wav");

        DatabaseService databaseService = new DatabaseService();
        try {
            databaseService.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection connection = databaseService.getConnection();
        SongRepositoryImpl songRepositoryImpl = new SongRepositoryImpl();
        /*try {
            boolean add = songRepositoryImpl.add(connection, song1);
          boolean add1 = songRepositoryImpl.add(connection, song2);
            System.out.println(add);
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }*/
        List<Song> songsFromDataBase = new ArrayList<>();
        try {
            songsFromDataBase = songRepositoryImpl.getAll(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(songsFromDataBase);
        musicPlayerService.play(songsFromDataBase.get(4).getFilePath());
    }
}