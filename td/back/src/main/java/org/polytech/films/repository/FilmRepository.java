package org.polytech.films.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polytech.films.model.Film;
import org.polytech.films.model.Film.Genre;
import org.springframework.stereotype.Repository;

@Repository 
public class FilmRepository {

    //private final FilmService filmService;
    private Map<Long, Film> MaBaseDeFilms;
    private Long prochainId = 1L;

    public Map<Long, Film> getMaBaseDeFilms() {
        return MaBaseDeFilms;
    }

    

    public void setMaBaseDeFilms(Map<Long, Film> maBaseDeFilms) {
        MaBaseDeFilms = maBaseDeFilms;
    }

    public Film RetourneFilmParId(Long id){

        return MaBaseDeFilms.get(id);
    }



    //Constructeur
    public FilmRepository() {

        Film film1 = new Film(prochainId, "Naruto", "Masashi Kishimoto", LocalDate.of(1999, 9, 21), Genre.ACTION);
        MaBaseDeFilms = new HashMap<>();
        this.MaBaseDeFilms.put(film1.getId(), film1);
    }



    public Film ajouterUnFilm(Film film) {
        prochainId++;
        film.setId(prochainId);
        this.MaBaseDeFilms.putIfAbsent(film.getId(), film);
        return film;
    }

    public Film mettreAJourUnFilm(Long id, Film film) {
        film.setId(id);
        MaBaseDeFilms.put(id, film);
        return film;
    }

    public void supprimerUnFilm(Long id) {
        MaBaseDeFilms.remove(id);
    }

    public List<Film> rechercherFilms(String realisateur, Genre genre) {
        return MaBaseDeFilms.values().stream()
            .filter(f -> realisateur == null || f.getRealisateur().equals(realisateur))
            .filter(f -> genre == null || f.getGenre() == genre)
            .toList();
    }





}
