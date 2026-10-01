package org.polytech.films.dto;

import java.time.LocalDate;
import java.util.Set;

import org.polytech.films.model.Genre;

public record FilmDetailDto(Long id, String titre, String realisateur, LocalDate dateSortie, Genre genre, Set<ActeurDto> acteurs) {
}
