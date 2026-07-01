package org.example.repo;

import org.example.model.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepoInMemImpl implements ArtistRepo {

    // CHANGE:
    // Replaced the fixed-size Artist[] array with an ArrayList.
    // ArrayList can grow as more artists are added, so we do not need maxArtists or nextArtist anymore.
    private List<Artist> artists = new ArrayList<>();

    public ArtistRepoInMemImpl(int maxArtists) {
        // CHANGE:
        // maxArtists is no longer needed because ArrayList grows automatically.
        // We are keeping this constructor so the factory and tests still work.
    }

    @Override
    public Artist[] getAllArtists() {
        // CHANGE:
        // The interface still expects Artist[], so we convert the ArrayList back into an array.
        return artists.toArray(new Artist[0]);
    }

    @Override
    public Artist getArtist(int id) {
        // CHANGE:
        // This prevents the program from crashing if the user enters an invalid artist id.
        if (id < 0 || id >= artists.size()) {
            return null;
        }

        return artists.get(id);
    }

    @Override
    public void addArtist(Artist artist) {
        // CHANGE:
        // The id is now based on the current size of the list.
        // Then we add the artist to the ArrayList.
        artist.setId(artists.size());
        artists.add(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        // CHANGE:
        // This checks that the artist id exists before trying to update it.
        // If the id is invalid, it fails quietly instead of crashing.
        if (artist.getId() >= 0 && artist.getId() < artists.size()) {
            artists.set(artist.getId(), artist);
        }
    }
}