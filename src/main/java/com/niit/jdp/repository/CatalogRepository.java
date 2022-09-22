/*
 *Author Name: Sneha Shinde
 *Date: 9/21/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CatalogRepository {
    Scanner scanner = new Scanner(System.in);
    SongRepository songRepository = new SongRepository();
    PlaylistRepository playlistRepository = new PlaylistRepository();
    int choice;
    MusicPlayerService musicPlayerService = new MusicPlayerService();
    private Playlist playlist;

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void displayCatalog(Connection connection) throws SQLException {
        do {
            System.out.println("==================================================================");
            System.out.println("                   Outside the Jukebox                            ");
            System.out.println("==================================================================");
            System.out.println("                    1.view all songs");
            System.out.println("                    2.view songs by artist");
            System.out.println("                    3.view songs by genre");
            System.out.println("                    4.view playlist");
            System.out.println("                    5.add playlist");
            System.out.println("                    6.enter 0 to exit");
            choice = scanner.nextInt();
            if (choice == 1) {
                List<Song> songList = songRepository.getAll(connection);
                Collections.sort(songList, (o1, o2) -> o1.getSongName().compareTo(o2.getSongName()));
                songList.forEach(p -> {
                    System.out.println("                    " + p.getSongName());
                });
                choice = scanner.nextInt();
                String songPath = songList.get(choice - 1).getFilePath();
                musicPlayerService.setSongPath(songPath);
                musicPlayerService.play();
                do {
                    System.out.println("press 0 to stop, 1 to play/pause, 0 to go back to menu");
                    choice = scanner.nextInt();
                    if (choice == 0) {
                        musicPlayerService.stop();
                    } else if (choice == 1) {
                        musicPlayerService.pause();
                    }
                } while (choice != 0);
            } else if (choice == 2) {
                List<String> allArtistFromDatabase = songRepository.getAllArtistFromDatabase(connection);
                Collections.sort(allArtistFromDatabase);
                System.out.println("                    " + allArtistFromDatabase);
                choice = scanner.nextInt();
                String artistName = allArtistFromDatabase.get(choice - 1);
                List<Song> songList = songRepository.getByArtistName(connection, artistName);
                songList.forEach(song -> {
                    System.out.println("                    " + song.getSongName());
                });
                choice = scanner.nextInt();
                musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                musicPlayerService.play();

            } else if (choice == 3) {
                List<String> genreFromDatabase = songRepository.getGenreFromDatabase(connection);
                Collections.sort(genreFromDatabase);
                for (String genre : genreFromDatabase) {
                    System.out.println("                    " + genre);
                }
                choice = scanner.nextInt();
                String genreName = genreFromDatabase.get(choice - 1);
                List<Song> songList = songRepository.getByGenreName(connection, genreName);
                for (Song song : songList) {
                    System.out.println("                    " + song.getSongName());
                }
                choice = scanner.nextInt();
                musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                musicPlayerService.play();
            } else if (choice == 4) {
                List<Playlist> allPlaylist = playlistRepository.getAll(connection);
                Collections.sort(allPlaylist, ((o1, o2) -> o1.getPlaylistName().compareTo(o2.getPlaylistName())));
                for (Playlist playlist : allPlaylist) {
                    System.out.println("                    " + playlist.getPlaylistName());
                }
                choice = scanner.nextInt();
                int playlistId = allPlaylist.get(choice - 1).getPlaylistId();
                Playlist playlist = playlistRepository.getById(connection, playlistId);
                for (String songId : playlist.getSongList()) {
                    Song song = songRepository.getById(connection, Integer.parseInt(songId));
                    System.out.println("                    " + song.getSongName());
                }
                choice = scanner.nextInt();
                int songId = Integer.parseInt(playlist.getSongList().get(choice - 1));
                Song song = songRepository.getById(connection, songId);
                musicPlayerService.setSongPath(song.getFilePath());
                musicPlayerService.play();
            } else if (choice == 5) {
                List<Playlist> newPlaylist = playlistRepository.add(connection, playlist);

            }
            choice = scanner.nextInt();
        } while (choice != 0);
    }
}



