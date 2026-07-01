package org.example.repo;

import org.example.model.Album;
import org.example.model.Artist;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepoInMemImpl implements AlbumRepo {

    // CHANGE:
    // Replaced the fixed-size Album[] array with an ArrayList.
    // ArrayList can grow as more albums are added, so we do not need maxAlbums or nextAlbum anymore.
    private List<Album> albums = new ArrayList<>();

    public AlbumRepoInMemImpl(int maxAlbums) {
        // maxAlbums is no longer needed because ArrayList can grow.
        // Keeping this constructor so the existing code/tests still work.
    }

    @Override
    public Album[] getAllAlbums() {
        // CHANGE:
        // The interface still expects Album[], so we convert the ArrayList back into an array.
        return albums.toArray(new Album[0]);
    }

    @Override
    public Album getAlbum(int id) {
        // CHANGE:
        // This prevents the program from crashing if the user enters an invalid album id.
        if (id < 0 || id >= albums.size()) {
            return null;
        }

        return albums.get(id);
    }

    @Override
    public Album[] getAlbumsByArtist(Artist artist) {
        // count the albums by artist
        int count = 0;
        for (Album album : albums) {
            if (album.getArtist().getId() == artist.getId()) {
                count++;
            }
        }

        // declare a new array and index
        Album[] results = new Album[count];
        int index = 0;

        // loop through all albums and populate new array using index
        for (Album album : albums) {
            if (album.getArtist().getId() == artist.getId()) {
                results[index] = album;
                index++;
            }
        }

        return results;
    }

    @Override
    public void addAlbum(Album album) {
        // CHANGE:
        // The id is now based on the current size of the list.
        // Then we add the album to the ArrayList.
        album.setId(albums.size());
        albums.add(album);
    }

    @Override
    public void updateAlbum(Album album) {
        // CHANGE:
        // This checks that the album id exists before trying to update it.
        // If the id is invalid, it fails quietly instead of crashing.
        if (album.getId() >= 0 && album.getId() < albums.size()) {
            albums.set(album.getId(), album);
        }
    }
}