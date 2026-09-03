package org.apprenti.app_bff.client;

import java.util.List;
import java.util.stream.Collectors;

import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.dto.TmdbDiscoverResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TmdbClient {

    private final RestClient restClient;

    public TmdbClient(
            RestClient.Builder restClientBuilder,
            @Value("${tmdb.api.base-url}") String baseUrl,
            @Value("${tmdb.api.token}") String token
    ) {
        this.restClient = restClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + token
                )
                .build();
    }

    public List<MovieResult> discoverMovies(
            List<Integer> genreIds,
            Integer maxRuntimeMinutes
    ) {

        String genreFilter
                = genreIds == null || genreIds.isEmpty()
                ? null
                : genreIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("|"));

        TmdbDiscoverResponse response
                = restClient.get()
                        .uri(uriBuilder -> {

                            uriBuilder
                                    .path("/discover/movie")
                                    .queryParam("language", "en-US")
                                    .queryParam("include_adult", false)
                                    .queryParam("include_video", false)
                                    .queryParam(
                                            "sort_by",
                                            "popularity.desc"
                                    )
                                    .queryParam(
                                            "vote_count.gte",
                                            100
                                    );

                            if (genreFilter != null) {
                                uriBuilder.queryParam(
                                        "with_genres",
                                        genreFilter
                                );
                            }

                            if (maxRuntimeMinutes != null) {
                                uriBuilder.queryParam(
                                        "with_runtime.lte",
                                        maxRuntimeMinutes
                                );
                            }

                            return uriBuilder.build();
                        })
                        .retrieve()
                        .body(TmdbDiscoverResponse.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results();
    }
}
