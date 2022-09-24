/*
 *Author Name: Sneha Shinde
 *Date: 9/21/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFoundException;
import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CatalogRepository {
    Scanner scanner = new Scanner(System.in);
    SongRepository songRepository = new SongRepository();
    PlaylistRepository playlistRepository = new PlaylistRepository();
    int choice;
    MusicPlayerService musicPlayerService = new MusicPlayerService();
    private Playlist playlist;

    private String name;

    private List<Song> songList;

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void displayCatalog(Connection connection) throws SQLException, SongNotFoundException, PlaylistNotFoundException {
        do {
            displayMenu();
            switch (choice) {
                case 1: {
                    displayHeader("Songs Library");
                    List<Song> songList = songRepository.getAll(connection);
                    Collections.sort(songList, Comparator.comparing(Song::getSongId));
                    songList.forEach(song -> {
                        System.out.println("                    " + song.getSongId() + ". " +
                                "" + song.getSongName());
                    });
                    System.out.println("Choose the song you wish to play : ");
                    choice = scanner.nextInt();
                    if (choice <= songList.size()) {
                        String songPath = songList.get(choice - 1).getFilePath();
                        musicPlayerService.setSongPath(songPath);
                        musicPlayerService.play();
                        int songChoice;
                        do {
                            songChoice = scanner.nextInt();
                            playerControls(songChoice);
                        } while (songChoice != 2);
                    } else {
                        throw new SongNotFoundException("Choose the songs only from the library!");
                    }
                    break;
                }
                case 2: {
                    List<String> allArtistFromDatabase = songRepository.getAllArtistFromDatabase(connection);
                    Collections.sort(allArtistFromDatabase);
                    displayHeader("Artist Library");
                    System.out.println("                    " + allArtistFromDatabase);
                    choice = scanner.nextInt();
                    String artistName = allArtistFromDatabase.get(choice - 1);
                    List<Song> songList = songRepository.getByArtistName(connection, artistName);
                    displayHeader("Song by " + artistName);
                    songList.forEach(song -> {
                        System.out.println("                    " + song.getSongName());
                    });
                    choice = scanner.nextInt();
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    int songChoice;
                    do {
                        songChoice = scanner.nextInt();
                        playerControls(songChoice);
                    } while (songChoice != 2);

                    break;
                }
                case 3: {
                    List<String> genreFromDatabase = songRepository.getGenreFromDatabase(connection);
                    Collections.sort(genreFromDatabase);
                    displayHeader("Genre");
                    for (int i = 1; i < genreFromDatabase.size() - 1; i++) {
                        String genre = genreFromDatabase.get(i);
                        System.out.println("                    " + i + "." + genre);
                    }
                    choice = scanner.nextInt();
                    String genreName = genreFromDatabase.get(choice);
                    List<Song> songList = songRepository.getByGenreName(connection, genreName);
                    displayHeader(genreName + " Songs");
                    for (Song song : songList) {
                        System.out.println("                    " + song.getSongName());
                    }
                    choice = scanner.nextInt();
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    int songChoice;
                    do {
                        songChoice = scanner.nextInt();
                        playerControls(songChoice);
                    } while (songChoice != 2);
                    break;
                }
                case 4: {
                    List<Playlist> allPlaylist = playlistRepository.getAll(connection);
                    Collections.sort(allPlaylist, (Comparator.comparing(Playlist::getPlaylistName)));
                    displayHeader("Playlist Library");
                    for (Playlist playlist : allPlaylist) {
                        System.out.println("                    " + playlist.getPlaylistName());
                    }
                    choice = scanner.nextInt();
                    if (choice >= allPlaylist.size()) {
                        int playlistId = allPlaylist.get(choice - 1).getPlaylistId();
                        Playlist playlist = playlistRepository.getById(connection, playlistId);
                        displayHeader("Songs in " + playlist.getPlaylistName());
                        for (String songId : playlist.getSongList()) {
                            Song song = songRepository.getById(connection, Integer.parseInt((songId)));
                            System.out.println("                    " + song.getSongName());
                        }
                        choice = scanner.nextInt();
                        String songId = (playlist.getSongList().get(choice - 1));
                        Song song = songRepository.getById(connection, Integer.parseInt(songId));
                        musicPlayerService.setSongPath(song.getFilePath());
                        musicPlayerService.play();
                        int songChoice;
                        do {
                            songChoice = scanner.nextInt();
                            playerControls(songChoice);
                        } while (songChoice != 2);
                    } else {
                        throw new PlaylistNotFoundException("Play songs from available Playlists!!");
                    }
                    break;
                }
                case 5: {
                    displayHeader("!!!Create your own Playlist!!!");
                    System.out.println("Enter name of Playlist");
                    Playlist playlist1 = new Playlist();
                    playlist1.setPlaylistName(scanner.next());
                    List<Song> songList = songRepository.getAll(connection);
                    Collections.sort(songList, Comparator.comparing(Song::getSongId));
                    songList.forEach(song -> {
                        System.out.println("                    " + song.getSongId() + ". " +
                                "" + song.getSongName());
                    });
                    List<String> songs = new ArrayList<>();
                    songList = songRepository.getAll(connection);
                    do {
                        System.out.println("Enter the song id's you want to add in the playlist or enter 0 to go back");
                        choice = scanner.nextInt();
                        songs.add(String.valueOf(choice));
                    } while (choice != 0);
                    playlist1.setSongList(songs);
                    playlistRepository.add(connection, playlist1);
                    System.out.println("playlist has been added successfully!");
                    break;
                }
                default:
                    System.err.println("Invalid choice!");
            }
            choice = scanner.nextInt();
        } while (choice != 0);
    }

    public void displayMenu() {
        displayHeader("!!!Outside the Jukebox!!!");
        System.out.println("                    1.view all songs");
        System.out.println("                    2.view songs by artist");
        System.out.println("                    3.view songs by genre");
        System.out.println("                    4.view playlist");
        System.out.println("                    5.add playlist");
        System.out.println("                    6.enter 0 to exit");
        System.out.println("Please enter your choice : ");
        choice = scanner.nextInt();
    }

    public void displayHeader(String title) {
        System.out.println("==================================================================");
        System.out.println("                    " + title + "                          ");
        System.out.println("==================================================================");
    }

    public void playerControls(int songChoice) {
        System.out.println("Press 1 to pause/play, 2 to stop");
        if (songChoice == 2) {
            musicPlayerService.stop();
            System.out.println("Press any key to go back to menu");
        } else if (songChoice == 1) {
            musicPlayerService.pause();
        } else {
            System.err.println("Invalid Choice!!");
        }
    }
}



