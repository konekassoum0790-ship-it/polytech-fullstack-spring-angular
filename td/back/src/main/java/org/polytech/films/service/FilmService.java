package org.polytech.films.service;

import java.util.Map;

import org.polytech.films.model.Film;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service 
public class FilmService {


    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    

    public Map<Long, Film > RetournerListeFilm(){
        return filmRepository.getMaBaseDeFilms();
    }

    public Film RetourneFilmParId(Long id){

        return filmRepository.RetourneFilmParId(id);
    }


    public Film ajouterUnFilm(Film film) {
        return filmRepository.ajouterUnFilm(film);
    }
}
