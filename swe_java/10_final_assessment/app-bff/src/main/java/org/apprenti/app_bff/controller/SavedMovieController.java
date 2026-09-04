package org.apprenti.app_bff.controller;

import java.security.Principal;
import java.util.List;

import org.apprenti.app_bff.dto.SaveMovieRequest;
import org.apprenti.app_bff.dto.UpdateSavedMovieRequest;
import org.apprenti.app_bff.model.SavedMovie;
import org.apprenti.app_bff.service.SavedMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saved-movies")
public class SavedMovieController {

    private final SavedMovieService savedMovieService;

    public SavedMovieController(
            SavedMovieService savedMovieService
    ) {
        this.savedMovieService = savedMovieService;
    }

    @PostMapping
    public SavedMovie saveMovie(
            Principal principal,
            @RequestBody SaveMovieRequest request
    ) {
        return savedMovieService.saveMovie(
                principal.getName(),
                request.tmdbMovieId()
        );
    }

    @GetMapping
    public List<SavedMovie> findSavedMovies(
            Principal principal
    ) {
        return savedMovieService.findSavedMovies(
                principal.getName()
        );
    }

    @PutMapping("/{savedMovieId}")
    public SavedMovie updateSavedMovie(
            Principal principal,
            @PathVariable Long savedMovieId,
            @RequestBody UpdateSavedMovieRequest request
    ) {
        return savedMovieService.updateSavedMovie(
                principal.getName(),
                savedMovieId,
                request.watchStatus(),
                request.personalRating(),
                request.notes()
        );
    }

    @DeleteMapping("/{savedMovieId}")
    public ResponseEntity<Void> deleteSavedMovie(
            Principal principal,
            @PathVariable Long savedMovieId
    ) {
        boolean deleted
                = savedMovieService.deleteSavedMovie(
                        principal.getName(),
                        savedMovieId
                );

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
