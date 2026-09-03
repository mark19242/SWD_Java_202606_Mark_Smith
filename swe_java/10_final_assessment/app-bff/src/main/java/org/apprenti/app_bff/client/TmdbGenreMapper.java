package org.apprenti.app_bff.client;

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
}
