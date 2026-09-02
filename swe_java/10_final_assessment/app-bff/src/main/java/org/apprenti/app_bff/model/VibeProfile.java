package org.apprenti.app_bff.model;

import java.util.EnumMap;
import java.util.Map;

public class VibeProfile {

    private final EnumMap<Genre, Integer> genreWeights;
    private Integer maxRuntimeMinutes;
    private boolean surpriseMe;

    public VibeProfile() {
        genreWeights = new EnumMap<>(Genre.class);

        for (Genre genre : Genre.values()) {
            genreWeights.put(genre, 0);
        }
    }

    public void adjustGenreWeight(
            Genre genre,
            int adjustment
    ) {
        genreWeights.merge(
                genre,
                adjustment,
                Integer::sum
        );
    }

    public int getGenreWeight(Genre genre) {
        return genreWeights.getOrDefault(
                genre,
                0
        );
    }

    public Map<Genre, Integer> getGenreWeights() {
        return Map.copyOf(genreWeights);
    }

    public Integer getMaxRuntimeMinutes() {
        return maxRuntimeMinutes;
    }

    public void setMaxRuntimeMinutes(
            Integer maxRuntimeMinutes
    ) {
        this.maxRuntimeMinutes
                = maxRuntimeMinutes;
    }

    public boolean isSurpriseMe() {
        return surpriseMe;
    }

    public void setSurpriseMe(boolean surpriseMe) {
        this.surpriseMe = surpriseMe;
    }
}
