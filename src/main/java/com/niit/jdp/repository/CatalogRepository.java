/*
 *Author Name: Sneha Shinde
 *Date: 9/21/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.exception.ArtistNotFoundException;
import com.niit.jdp.exception.GenreNotFoundException;
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

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayCatalog(Connection connection) throws SQLException, SongNotFoundException, PlaylistNotFoundException {
        choice = 1;
        while (choice != 0) {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 0: {
                    break;
                }
                case 1: {
                    int choice;
                    displayHeader("Songs Library");
                    List<Song> songList = songRepository.getAll(connection);
                    songList.sort(Comparator.comparing(Song::getSongId));
                    songList.forEach(song -> System.out.println(song.getSongId() + ". " + "" + song.getSongName()));
                    while (true) {
                        System.out.print("\nChoose the song you wish to play or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
                        try {
                            if (choice > 0 && choice <= songList.size()) {
                                break;
                            } else {
                                throw new SongNotFoundException("Choose the songs only from the library!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }

                    }
                    if (choice == 0) {
                        break;
                    }
                    String songPath = songList.get(choice - 1).getFilePath();
                    musicPlayerService.setSongPath(songPath);
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 2: {
                    int choice;
                    List<String> allArtistFromDatabase = songRepository.getAllArtistFromDatabase(connection);
                    Collections.sort(allArtistFromDatabase);
                    displayHeader("Artist Library");
                    int count = 1;
                    for (String artist : allArtistFromDatabase) {
                        System.out.println(count + ". " + artist);
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the Artist id or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
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
                    if (choice == 0) {
                        break;
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
                        System.out.print("\nChoose the song you wish to play or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
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
                    if (choice == 0) {
                        break;
                    }
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 3: {
                    int choice;
                    List<String> genreFromDatabase = songRepository.getGenreFromDatabase(connection);
                    Collections.sort(genreFromDatabase);
                    displayHeader("Genre");
                    for (int i = 0; i < genreFromDatabase.size(); i++) {
                        String genre = genreFromDatabase.get(i);
                        System.out.println(i + 1 + "." + genre);
                    }
                    while (true) {
                        System.out.print("\nChoose the genre or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
                        try {
                            if (choice > 0 && choice <= genreFromDatabase.size()) {
                                break;
                            } else {
                                throw new GenreNotFoundException("Choose the genre from above options!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }

                    }
                    if (choice == 0) {
                        break;
                    }
                    String genreName = genreFromDatabase.get(choice - 1);
                    List<Song> songList = songRepository.getByGenreName(connection, genreName);
                    displayHeader(genreName + " Songs");
                    int count = 1;
                    for (Song song : songList) {
                        System.out.println(count + ". " + song.getSongName());
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the song you wish to play or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
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
                    if (choice == 0) {
                        break;
                    }
                    musicPlayerService.setSongPath(songList.get(choice - 1).getFilePath());
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 4: {
                    int choice;
                    List<Playlist> allPlaylist = playlistRepository.getAll(connection);
                    allPlaylist.sort((Comparator.comparing(Playlist::getPlaylistName)));
                    displayHeader("Playlist Library");
                    int count = 1;
                    for (Playlist playlist : allPlaylist) {
                        System.out.println(count + ". " + playlist.getPlaylistName());
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the Playlist ID or press 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        try {
                            if (choice == 0) {
                                break;
                            }
                            if (choice > 0 && choice <= allPlaylist.size()) {
                                break;
                            } else {
                                throw new PlaylistNotFoundException("Choose the playlist from above options!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }

                    }
                    if (choice == 0) {
                        break;
                    }
                    int playlistId = allPlaylist.get(choice - 1).getPlaylistId();
                    Playlist playlist = playlistRepository.getById(connection, playlistId);
                    displayHeader("Songs in " + playlist.getPlaylistName());
                    count = 1;
                    for (String songId : playlist.getSongList()) {
                        Song song = songRepository.getById(connection, Integer.parseInt((songId)));
                        System.out.println(count + ". " + song.getSongName());
                        count++;
                    }
                    while (true) {
                        System.out.print("\nChoose the song from " + playlist.getPlaylistName() + " playlist or enter 0 to go back to the menu : ");
                        choice = scanner.nextInt();
                        if (choice == 0) {
                            break;
                        }
                        try {
                            if (choice > 0 && choice <= playlist.getSongList().size()) {
                                break;
                            } else {
                                throw new SongNotFoundException("Choose the songs from above options!");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                            System.out.println();
                        }
                    }
                    if (choice == 0) {
                        break;
                    }
                    String songId = (playlist.getSongList().get(choice - 1));
                    Song song = songRepository.getById(connection, Integer.parseInt(songId));
                    musicPlayerService.setSongPath(song.getFilePath());
                    musicPlayerService.play();
                    playerControls();
                    break;
                }
                case 5: {
                    int choice;
                    displayHeader("!!!Create your own Playlist!!!");
                    System.out.print("Enter name of Playlist : ");
                    Playlist playlist1 = new Playlist();
                    scanner.nextLine();
                    playlist1.setPlaylistName(scanner.nextLine());
                    List<Song> songList = songRepository.getAll(connection);
                    songList.sort(Comparator.comparing(Song::getSongId));
                    songList.forEach(song -> System.out.println(song.getSongId() + ". " + "" + song.getSongName()));
                    List<String> songs = new ArrayList<>();
                    System.out.println("\nEnter the song id's you want to add in the playlist and enter 0 after adding the songs!");
                    do {
                        choice = scanner.nextInt();
                        try {
                            if (choice > 0 && choice <= songList.size()) {
                                songs.add(String.valueOf(choice));
                                for (Song song : songList) {
                                    if (song.getSongId() == choice) {
                                        System.out.println("Song " + song.getSongId() + "." + song.getSongName() + " has been added successfully in " + playlist1.getPlaylistName() + " playlist!");
                                    }
                                }
                            } else if (choice != 0) {
                                throw new SongNotFoundException("\nPlease choose songs from above list");
                            }
                        } catch (Exception exception) {
                            System.err.println(exception.getMessage());
                        }
                    } while (choice != 0);
                    if (songs.size() > 0) {
                        playlist1.setSongList(songs);
                        if (playlistRepository.add(connection, playlist1)) {
                            System.out.println("Playlist has been created successfully!");
                        }
                    } else {
                        System.out.println("Playlist not created!");
                    }
                    break;
                }
                default:
                    System.err.println("Invalid choice!");
            }
        }
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
        //choice = scanner.nextInt();
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
                //System.out.println("Press 9 to go back to the main menu");
            } else if (songChoice == 1) {
                musicPlayerService.pause();
            } else {
                System.err.println("Invalid Choice!!");
            }
        } while (songChoice != 2);
    }
}



