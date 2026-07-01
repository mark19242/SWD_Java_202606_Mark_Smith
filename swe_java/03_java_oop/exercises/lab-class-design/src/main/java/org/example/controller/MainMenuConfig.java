package org.example.controller;

import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.repo.UserRepo;
import org.example.view.ConsoleIO;

public class MainMenuConfig {

    // This class groups together everything the MainMenuController needs.
    // This keeps the MainMenuController constructor from getting too many parameters.
    private ConsoleIO io;
    private AlbumRepo albums;
    private ArtistRepo artists;
    private UserRepo users;

    public MainMenuConfig(ConsoleIO io, AlbumRepo albums, ArtistRepo artists, UserRepo users) {
        // Store the dependencies so the controller can access them from one object.
        this.io = io;
        this.albums = albums;
        this.artists = artists;
        this.users = users;
    }

    public ConsoleIO getIo() {
        return io;
    }

    public AlbumRepo getAlbums() {
        return albums;
    }

    public ArtistRepo getArtists() {
        return artists;
    }

    public UserRepo getUsers() {
        return users;
    }
}