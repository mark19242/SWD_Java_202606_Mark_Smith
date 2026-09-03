package org.apprenti.app_bff.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieResult(
        Long id,
        String title,
        String overview,
        @JsonProperty("poster_path")
        String posterPath,
        @JsonProperty("genre_ids")
        List<Integer> genreIds,
        @JsonProperty("vote_average")
        Double voteAverage,
        @JsonProperty("vote_count")
        Integer voteCount,
        @JsonProperty("release_date")
        String releaseDate
        ) {

}
