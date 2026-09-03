package org.apprenti.app_bff.client;

import java.util.Optional;

import org.apprenti.app_bff.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class TmdbGenreMapper {

    public int toTmdbId(Genre genre) {

        if (genre == null) {
            throw new IllegalArgumentException(
                    "Genre is required."
            );
        }

        return switch (genre) {

            case ACTION ->
                28;
            case ADVENTURE ->
                12;
            case ANIMATION ->
                16;
            case COMEDY ->
                35;
            case CRIME ->
                80;
            case DOCUMENTARY ->
                99;
            case DRAMA ->
                18;
            case FAMILY ->
                10751;
            case FANTASY ->
                14;
            case HISTORY ->
                36;
            case HORROR ->
                27;
            case MYSTERY ->
                9648;
            case ROMANCE ->
                10749;
            case SCIENCE_FICTION ->
                878;
            case THRILLER ->
                53;
            case WAR ->
                10752;
        };
    }

    public Optional<Genre> fromTmdbId(int tmdbId) {

        return switch (tmdbId) {
            case 28 ->
                Optional.of(Genre.ACTION);
            case 12 ->
                Optional.of(Genre.ADVENTURE);
            case 16 ->
                Optional.of(Genre.ANIMATION);
            case 35 ->
                Optional.of(Genre.COMEDY);
            case 80 ->
                Optional.of(Genre.CRIME);
            case 99 ->
                Optional.of(Genre.DOCUMENTARY);
            case 18 ->
                Optional.of(Genre.DRAMA);
            case 10751 ->
                Optional.of(Genre.FAMILY);
            case 14 ->
                Optional.of(Genre.FANTASY);
            case 36 ->
                Optional.of(Genre.HISTORY);
            case 27 ->
                Optional.of(Genre.HORROR);
            case 9648 ->
                Optional.of(Genre.MYSTERY);
            case 10749 ->
                Optional.of(Genre.ROMANCE);
            case 878 ->
                Optional.of(Genre.SCIENCE_FICTION);
            case 53 ->
                Optional.of(Genre.THRILLER);
            case 10752 ->
                Optional.of(Genre.WAR);
            default ->
                Optional.empty();
        };
    }

}
