/*
 *Author Name: Sneha Shinde
 *Date: 9/21/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.exception.ArtistNotFoundException;
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
                        System.out.println(song.getSongId() + ". " +
                                "" + song.getSongName());
                    });
                    while (true) {
                        System.out.print("\nChoose the song you wish to play : ");
                        choice = scanner.nextInt();
                        try {
                            if (choice > 0 && choice <= songList.size()) {
                                break;
                            } else {
                                throw new SongNotFoundException("\nChoose the songs only from the library!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                        }

                    }
                    String songPath = songList.get(choice - 1).getFilePath();
                    musicPlayerService.setSongPath(songPath);
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 2: {
                    List<String> allArtistFromDatabase = songRepository.getAllArtistFromDatabase(connection);
                    Collections.sort(allArtistFromDatabase);
                    displayHeader("Artist Library");
                    int count = 1;
                    for (String artist : allArtistFromDatabase) {
                        System.out.println(count + ". " + artist);
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the Artist id : ");
                        choice = scanner.nextInt();
                        try {
                            if (choice > 0 && choice <= allArtistFromDatabase.size()) {
                                break;
                            } else {
                                throw new ArtistNotFoundException("Choose the artists from above options!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }

                    }
                    String artistName = allArtistFromDatabase.get(choice - 1);
                    List<Song> songList = songRepository.getByArtistName(connection, artistName);
                    displayHeader("Song by " + artistName);
                    count = 1;
                    for (Song song : songList) {
                        System.out.println(count + ". " + song.getSongName());
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the song you wish to play : ");
                        choice = scanner.nextInt();
                        try {
                            if (choice > 0 && choice <= songList.size()) {
                                break;
                            } else {
                                throw new SongNotFoundException("Choose the songs from above options!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }

                    }
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 3: {
                    List<String> genreFromDatabase = songRepository.getGenreFromDatabase(connection);
                    Collections.sort(genreFromDatabase);
                    displayHeader("Genre");
                    for (int i = 1; i < genreFromDatabase.size() - 1; i++) {
                        String genre = genreFromDatabase.get(i);
                        System.out.println(i + "." + genre);
                    }
                    System.out.print("\nChoose the genre : ");
                    choice = scanner.nextInt();
                    String genreName = genreFromDatabase.get(choice);
                    List<Song> songList = songRepository.getByGenreName(connection, genreName);
                    displayHeader(genreName + " Songs");
                    int count = 1;
                    for (Song song : songList) {
                        System.out.println(count + ". " + song.getSongName());
                        count++;
                    }
                    System.out.print("\nChoose the song you wish to play: ");
                    choice = scanner.nextInt();
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 4: {
                    List<Playlist> allPlaylist = playlistRepository.getAll(connection);
                    Collections.sort(allPlaylist, (Comparator.comparing(Playlist::getPlaylistName)));
                    displayHeader("Playlist Library");
                    for (Playlist playlist : allPlaylist) {
                        System.out.println("                    " + playlist.getPlaylistName());
                    }
                    System.out.println("Choose the Playlist");
                    choice = scanner.nextInt();
                    if (choice <= allPlaylist.size()) {
                        int playlistId = allPlaylist.get(choice - 1).getPlaylistId();
                        Playlist playlist = playlistRepository.getById(connection, playlistId);
                        displayHeader("Songs in " + playlist.getPlaylistName());
                        for (String songId : playlist.getSongList()) {
                            Song song = songRepository.getById(connection, Integer.parseInt((songId)));
                            System.out.println("                    " + song.getSongName());
                        }
                        System.out.println("Choose the song from " + playlist.getPlaylistName());
                        choice = scanner.nextInt();
                        String songId = (playlist.getSongList().get(choice - 1));
                        Song song = songRepository.getById(connection, Integer.parseInt(songId));
                        musicPlayerService.setSongPath(song.getFilePath());
                        musicPlayerService.play();
                        // int songChoice;
                        //do {
                        //songChoice = scanner.nextInt();
                        playerControls();
                        //} while (songChoice != 2);
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
                        if (choice != 0) songs.add(String.valueOf(choice));
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
        System.out.println("1. View all songs");
        System.out.println("2. View songs by artist");
        System.out.println("3. View songs by genre");
        System.out.println("4. View playlist");
        System.out.println("5. Add playlist");
        System.out.println("6. Enter 0 to exit");
        System.out.print("\nPlease enter your choice : ");
        choice = scanner.nextInt();
    }

    public void displayHeader(String title) {
        System.out.println("==================================================================");
        System.out.println("                    " + title + "                          ");
        System.out.println("==================================================================");
    }

    public void playerControls() {
        int songChoice;
        System.out.println();
        do {
            System.out.println("Press 1 to pause/play, 2 to stop");
            songChoice = scanner.nextInt();
            if (songChoice == 2) {
                musicPlayerService.stop();
                System.out.println("Press 9 to go back to the main menu");
            } else if (songChoice == 1) {
                musicPlayerService.pause();
            } else {
                System.err.println("Invalid Choice!!");
            }
        } while (songChoice != 2);
    }
}



