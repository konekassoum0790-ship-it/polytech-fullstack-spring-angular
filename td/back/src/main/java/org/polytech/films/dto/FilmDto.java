package org.polytech.films.dto;

import java.time.LocalDate;

import org.polytech.films.model.Genre;

public record FilmDto(Long id, String titre, String realisateur, LocalDate dateSortie, Genre genre) {
}
