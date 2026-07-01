package org.example.controller;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.view.ConsoleIO;

public class MainMenuController {
    private ConsoleIO io;
    private AlbumRepo albums;
    private ArtistRepo artists;
    private static final int CHOICE_VIEW_ALL_ALBUMS = 1;
    private static final int CHOICE_VIEW_ALBUMS_BY_ARTIST = 2;
    private static final int CHOICE_VIEW_ALBUM = 3;
    private static final int CHOICE_QUIT = 4;
    private static final int MIN_CHOICE = 1;
    private static final int MAX_CHOICE = 4;


    // The controller now receives one config object instead of multiple separate dependencies.
    // This makes it easier to add more repositories later without changing the constructor every time.
    public MainMenuController(MainMenuConfig config) {
        this.albums = config.getAlbums();
        this.artists = config.getArtists();
        this.io = config.getIo();
    }

    public void run() {
        io.writeMessage("========================");
        io.writeMessage("Bodacious Music Database");
        io.writeMessage("========================");
        io.writeMessage("\n\n");

        boolean running = true;

        while (running) {
            printMainMenuHeader();
            int choice = io.getIntegerInBetween("> ", MIN_CHOICE, MAX_CHOICE);

            switch (choice) {
                case CHOICE_VIEW_ALL_ALBUMS:
                    viewAllAlbums();
                    break;
                case CHOICE_VIEW_ALBUMS_BY_ARTIST:
                    viewAlbumsByArtist();
                    break;
                case CHOICE_VIEW_ALBUM:
                    viewAlbum();
                    break;
                case CHOICE_QUIT:
                    io.writeMessage("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    private void viewAlbum() {
        int albumId = io.getInteger("Enter album id:");
        Album album = albums.getAlbum(albumId);
        if (album != null) {
            io.writeMessage(">>>> Album Details >>>>");
            io.writeMessage(album.getTitle());
            io.writeMessage(album.getArtist().getName());
            io.writeMessage("");
            for (Song s : album.getSongs()) {
                io.writeMessage(s.getId() + ": " + s.getTitle());
            }
        }
        io.writeMessage("");
    }

    private void viewAlbumsByArtist() {
        io.writeMessage(">>> Artists");
        for (Artist artist : artists.getAllArtists()) {
            io.writeMessage(artist.getId() + "  -  " + artist.getName());
        }

        int choice = io.getInteger("Select an artist");
        Artist artist = artists.getArtist(choice);

        if (artist == null) {
            io.writeMessage("Invalid artist selected.");
            return;
        }

        Album[] discography = albums.getAlbumsByArtist(artist);
        if (discography.length == 0) {
            io.writeMessage("No albums by this artist exist.");
            return;
        }

        for (Album album : discography) {
            io.writeMessage(album.getId() + "  -  " + album.getTitle());
        }
    }

    private void viewAllAlbums() {
        // List all the albums in the repository
        Album[] discography = albums.getAllAlbums();
        if (discography.length == 0) {
            io.writeMessage("No albums exist.");
            return;
        }

        for (Album album : discography) {
            io.writeMessage(album.getId() + "  -  " + album.getTitle() + "  -  " + album.getArtist().getName());
        }
    }

    public void printMainMenuHeader() {
        io.writeMessage(">>> Main Menu <<<");
        io.writeMessage(CHOICE_VIEW_ALL_ALBUMS + "  -  View All Albums");
        io.writeMessage(CHOICE_VIEW_ALBUMS_BY_ARTIST + "  -  View Albums by Artist");
        io.writeMessage(CHOICE_VIEW_ALBUM + "  -  View Album Details");
        io.writeMessage(CHOICE_QUIT + "  -  Quit");
    }
}
