package org.polytech.films.dto;

import java.util.Set;
import java.util.stream.Collectors;

import org.polytech.films.model.Film;

public class FilmMapper {

    public static FilmDto toDto(Film film) {
        return new FilmDto(film.getId(), film.getTitre(), film.getRealisateur(), film.getDateSortie(), film.getGenre());
    }

    public static FilmDetailDto toDetailDto(Film film) {
        Set<ActeurDto> acteurs = film.getActeurs().stream()
            .map(ActeurMapper::toDto)
            .collect(Collectors.toSet());
        return new FilmDetailDto(film.getId(), film.getTitre(), film.getRealisateur(), film.getDateSortie(), film.getGenre(), acteurs);
    }

    public static Film toEntity(FilmCreationDto dto) {
        Film film = new Film();
        film.setTitre(dto.titre());
        film.setRealisateur(dto.realisateur());
        film.setDateSortie(dto.dateSortie());
        film.setGenre(dto.genre());
        return film;
    }
}
