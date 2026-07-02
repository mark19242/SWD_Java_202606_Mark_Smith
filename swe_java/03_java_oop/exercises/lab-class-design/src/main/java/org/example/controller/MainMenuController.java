package org.example.controller;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
import org.example.model.User;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.repo.UserRepo;
import org.example.view.ConsoleIO;

public class MainMenuController {
    private ConsoleIO io;
    private AlbumRepo albums;
    private ArtistRepo artists;
    private UserRepo users;

    // Keeps track of the current logged-in user.
    // If this is null, nobody is logged in.
    private User currentUser;

    // CHANGE:
    // Replaced the separate menu number constants with an enum.
    // This keeps the menu number and display text together in one place.
    private enum MenuChoice {
        VIEW_ALL_ALBUMS(1, "View All Albums"),
        VIEW_ALBUMS_BY_ARTIST(2, "View Albums by Artist"),
        VIEW_ALBUM(3, "View Album Details"),
        LOGIN_OR_LOGOUT(4, "Log In"),
        QUIT(5, "Quit");

        private int value;
        private String displayText;

        MenuChoice(int value, String displayText) {
            this.value = value;
            this.displayText = displayText;
        }

        public int getValue() {
            return value;
        }

        public String getDisplayText() {
            return displayText;
        }

        // Finds the enum choice that matches the number the user typed.
        public static MenuChoice fromValue(int value) {
            for (MenuChoice choice : MenuChoice.values()) {
                if (choice.getValue() == value) {
                    return choice;
                }
            }

            return null;
        }
    }

    // The controller now receives one config object instead of multiple separate dependencies.
    // This makes it easier to add more repositories later without changing the constructor every time.
    public MainMenuController(MainMenuConfig config) {
        this.albums = config.getAlbums();
        this.artists = config.getArtists();
        this.users = config.getUsers();
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

            int choiceNumber = io.getIntegerInBetween("> ", 1, MenuChoice.values().length);
            MenuChoice choice = MenuChoice.fromValue(choiceNumber);

            if (choice == null) {
                io.writeMessage("Invalid choice.");
                continue;
            }

            switch (choice) {
                case VIEW_ALL_ALBUMS:
                    viewAllAlbums();
                    break;
                case VIEW_ALBUMS_BY_ARTIST:
                    viewAlbumsByArtist();
                    break;
                case VIEW_ALBUM:
                    viewAlbum();
                    break;
                case LOGIN_OR_LOGOUT:
                    loginOrLogout();
                    break;
                case QUIT:
                    io.writeMessage("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    private void loginOrLogout() {
        // If nobody is logged in, show the user list and allow the user to log in.
        if (currentUser == null) {
            io.writeMessage(">>> Users <<<");

            for (User user : users.getAllUsers()) {
                io.writeMessage(user.getId() + "  -  " + user.getName());
            }

            int userId = io.getInteger("Select a user id: ");
            User selectedUser = users.getUser(userId);

            if (selectedUser == null) {
                io.writeMessage("Invalid user selected.");
                return;
            }

            currentUser = selectedUser;
            io.writeMessage("Logged in as: " + currentUser.getName());
            return;
        }

        // If someone is already logged in, this same menu option logs them out.
        io.writeMessage("Logged out: " + currentUser.getName());
        currentUser = null;
    }

    private void viewAlbum() {
        int albumId = io.getInteger("Enter album id:");
        Album album = albums.getAlbum(albumId);

        if (album == null) {
            io.writeMessage("Invalid album selected.");
            return;
        }

        io.writeMessage(">>>> Album Details >>>>");
        io.writeMessage(album.getTitle());
        io.writeMessage(album.getArtist().getName());
        io.writeMessage("");

        for (Song song : album.getSongs()) {
            io.writeMessage(song.getId() + ": " + song.getTitle());
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
        // List all the albums in the repository.
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

        // Show the login status at the top of the menu.
        if (currentUser == null) {
            io.writeMessage("Logged in: No user selected");
        } else {
            io.writeMessage("Logged in as: " + currentUser.getName());
        }

        for (MenuChoice choice : MenuChoice.values()) {
            String displayText = choice.getDisplayText();

            // The same menu option changes text depending on login status.
            if (choice == MenuChoice.LOGIN_OR_LOGOUT) {
                displayText = getLoginMenuText();
            }

            io.writeMessage(choice.getValue() + "  -  " + displayText);
        }
    }

    private String getLoginMenuText() {
        // If nobody is logged in, the menu should say Log In.
        // If someone is logged in, the menu should say Log Out.
        if (currentUser == null) {
            return "Log In";
        }

        return "Log Out";
    }
}