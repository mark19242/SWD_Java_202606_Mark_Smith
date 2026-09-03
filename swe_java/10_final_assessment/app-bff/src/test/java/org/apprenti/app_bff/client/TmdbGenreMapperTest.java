package org.apprenti.app_bff.client;

import org.apprenti.app_bff.model.Genre;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TmdbGenreMapperTest {

    private final TmdbGenreMapper mapper
            = new TmdbGenreMapper();

    @Test
    void shouldMapReelVibeGenresToTmdbIds() {

        assertEquals(28, mapper.toTmdbId(Genre.ACTION));
        assertEquals(35, mapper.toTmdbId(Genre.COMEDY));
        assertEquals(27, mapper.toTmdbId(Genre.HORROR));
        assertEquals(10749, mapper.toTmdbId(Genre.ROMANCE));
        assertEquals(
                878,
                mapper.toTmdbId(Genre.SCIENCE_FICTION)
        );
    }

    @Test
    void nullGenreShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> mapper.toTmdbId(null)
                );

        assertEquals(
                "Genre is required.",
                exception.getMessage()
        );
    }
}
