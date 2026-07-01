package org.example;

import org.example.controller.MainMenuConfig;
import org.example.controller.MainMenuController;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.repo.factory.AlbumRepoFactory;
import org.example.repo.factory.ArtistRepoFactory;
import org.example.view.ConsoleIO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleIO io = new ConsoleIO();
        AlbumRepo albums = AlbumRepoFactory.instance();
        ArtistRepo artists = ArtistRepoFactory.instance();

        // Create a config object to group everything the main menu needs.
        MainMenuConfig config = new MainMenuConfig(io, albums, artists);

        // Pass the config object into the controller instead of passing each dependency separately.
        MainMenuController mainMenu = new MainMenuController(config);

        // Start the menu loop.
        mainMenu.run();



    }
}
