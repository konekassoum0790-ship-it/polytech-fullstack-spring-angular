package org.polytech.films.dto;

import java.time.LocalDate;

import org.polytech.films.model.Genre;

import jakarta.validation.constraints.NotBlank;

public record FilmCreationDto(@NotBlank String titre, String realisateur, LocalDate dateSortie, Genre genre) {
}
